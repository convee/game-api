package com.company.project.model;

import javax.persistence.*;

public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 设备id
     */
    private String did;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Integer createTime;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取设备id
     *
     * @return did - 设备id
     */
    public String getDid() {
        return did;
    }

    /**
     * 设置设备id
     *
     * @param did 设备id
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}