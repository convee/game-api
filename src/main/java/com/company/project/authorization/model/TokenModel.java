package com.company.project.authorization.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 */
@Setter
@Getter
public class TokenModel {

    private Integer userId;

    private String token;

    public TokenModel(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
