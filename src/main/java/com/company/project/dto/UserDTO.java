package com.company.project.dto;

import lombok.Getter;
import lombok.Setter;

public @Setter @Getter class UserDTO {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 设备id
     */
    private String did;

    /**
     * 注册时间
     */
    private Integer createTime;

}