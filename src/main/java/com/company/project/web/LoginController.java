package com.company.project.web;

import com.company.project.authorization.manager.TokenManager;
import com.company.project.authorization.model.TokenModel;
import com.company.project.cache.PlayerCache;
import com.company.project.core.*;
import com.company.project.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录控制器
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private PlayerCache playerCache;
    @Autowired
    private PlayerService playerService;

    /**
     * 游客登录
     * @param did
     * @return
     */
    @GetMapping("/login/tourist")
    public Object tourist(@RequestParam String did) {

        Integer userId = playerCache.getUserIdByDid(did);
        if (userId == null || userId <= 0) {
            //userId生成
            userId = playerCache.incrUserId();
            //添加账号
            playerCache.createAccount(userId, did);
            //添加玩家信息
            playerCache.addPlayer(userId);
        }
        TokenModel tokenModel = tokenManager.createToken(userId);
        return (new Ret())
                .setOption("userId", userId)
                .setOption("token", tokenModel.getToken())
                .setOption("playerInfo", playerService.getPlayerInfo(userId))
                .getResult();
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @GetMapping("/logout")
    public Object logout(@RequestParam String token, @RequestParam Integer userId) {
        tokenManager.checkLogin(token, userId);
        tokenManager.deleteToken(userId);
        return (new Ret()).getResult();
    }
}
