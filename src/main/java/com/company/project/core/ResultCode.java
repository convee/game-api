package com.company.project.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    //token不存在或已过期
    TOKEN_EXPIRE(10001),
    USER_NOT_FOUND(10002),
    NICKNAME_EXISTS(10003),
    REGISTER_FAILED(10004),

    //订单创建失败
    ORDER_CREATE_FAILED(20001),
    //订单不存在
    ORDER_NOT_EXISTS(20002),
    //订单支付失败
    ORDER_PAY_FAILED(20003),

    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);//服务器内部错误



    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
