package com.company.project.authorization.manager.impl;

import com.company.project.authorization.manager.TokenManager;
import com.company.project.authorization.model.TokenModel;
import com.company.project.cache.PlayerCache;
import com.company.project.core.ServiceException;
import com.company.project.core.TokenExpiredException;
import java.util.UUID;
import javax.annotation.Resource;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.springframework.stereotype.Component;

/**
 * 通过ssdb存储和验证token的实现类
 * @see TokenManager
 */

@Component
public class SSDBTokenManager implements TokenManager {

    @Resource
    private SSDB ssdb;
    @Resource
    private PlayerCache playerCache;

    private final static Integer TOKEN_EXPIRES = 10 * 3600;
    private final static String TOKEN_PRE = "token:";


    @Override
    public TokenModel createToken(Integer userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        //存储到SSDB并设置过期时间
        String tokenKey = TOKEN_PRE + userId;
        Response ssdbResp = ssdb.setx(tokenKey, token, TOKEN_EXPIRES);
        if (!ssdbResp.ok()) {
            throw new ServiceException("token创建失败");
        }
        return model;
    }

    @Override
    public TokenModel getToken(String token, Integer userId) {
        if (token == null || userId == null) {
            return null;
        }
        return new TokenModel(userId, token);
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String tokenKey = TOKEN_PRE + model.getUserId();
        Response ssdbResp = ssdb.get(tokenKey);
        if (!ssdbResp.ok()) {
            throw new ServiceException("获取token失败");
        }
        String token = ssdbResp.asString();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        ssdb.setx(tokenKey, token, TOKEN_EXPIRES);
        return true;
    }

    @Override
    public void deleteToken(Integer userId) {
        String tokenKey = TOKEN_PRE + userId;
        ssdb.del(tokenKey);
    }
    @Override
    public void checkLogin(String token, Integer userId) {
        TokenModel model = new TokenModel(userId, token);
        if (!this.checkToken(model)) {
            throw new TokenExpiredException();
        }
        if (playerCache.checkUid(userId)) {
            throw new ServiceException("用户不存在");
        }
    }
}
