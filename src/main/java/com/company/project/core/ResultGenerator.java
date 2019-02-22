package com.company.project.core;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS);
    }

    public static Result genFailResult() {
        return new Result()
                .setCode(ResultCode.FAIL);
    }
}
