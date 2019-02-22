package com.company.project.authorization.manager;

import com.company.project.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     * @param userId 指定用户的id
     * @return 生成的token
     */
    TokenModel createToken(Integer userId);

    /**
     * 检查token是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * @param token token
     * @param userId userId
     * @return
     */
    TokenModel getToken(String token, Integer userId);

    /**
     * 清除token
     * @param userId 登录用户的id
     */
    void deleteToken(Integer userId);

    /**
     * 检测登录态
     * @param token
     * @param userId
     */
    void checkLogin(String token, Integer userId);

}