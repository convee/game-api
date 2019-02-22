package com.company.project.model;

import javax.persistence.*;

public class Orders {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 支付方式(1苹果支付)
     */
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 支付状态(0未付款，1已付款，2已到帐)
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 订单类型(1宝石)
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 订单金额
     */
    @Column(name = "order_amount")
    private Float orderAmount;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 下单时间
     */
    @Column(name = "create_time")
    private Integer createTime;

    /**
     * 订单更新时间
     */
    @Column(name = "update_time")
    private Integer updateTime;

    /**
     * 描述
     */
    private String brief;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 系统类型(1ios，2android)
     */
    @Column(name = "operation_sys")
    private Integer operationSys;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单号
     *
     * @return order_number - 订单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单号
     *
     * @param orderNumber 订单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取支付方式(1苹果支付)
     *
     * @return pay_type - 支付方式(1苹果支付)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置支付方式(1苹果支付)
     *
     * @param payType 支付方式(1苹果支付)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    /**
     * 获取支付状态(0未付款，1已付款，2已到帐)
     *
     * @return pay_status - 支付状态(0未付款，1已付款，2已到帐)
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态(0未付款，1已付款，2已到帐)
     *
     * @param payStatus 支付状态(0未付款，1已付款，2已到帐)
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取订单类型(1宝石)
     *
     * @return order_type - 订单类型(1宝石)
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型(1宝石)
     *
     * @param orderType 订单类型(1宝石)
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取订单金额
     *
     * @return order_amount - 订单金额
     */
    public Float getOrderAmount() {
        return orderAmount;
    }

    /**
     * 设置订单金额
     *
     * @param orderAmount 订单金额
     */
    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

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
     * 获取下单时间
     *
     * @return create_time - 下单时间
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * 设置下单时间
     *
     * @param createTime 下单时间
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单更新时间
     *
     * @return update_time - 订单更新时间
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置订单更新时间
     *
     * @param updateTime 订单更新时间
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取描述
     *
     * @return brief - 描述
     */
    public String getBrief() {
        return brief;
    }

    /**
     * 设置描述
     *
     * @param brief 描述
     */
    public void setBrief(String brief) {
        this.brief = brief;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取系统类型(1ios，2android)
     *
     * @return operation_sys - 系统类型(1ios，2android)
     */
    public Integer getOperationSys() {
        return operationSys;
    }

    /**
     * 设置系统类型(1ios，2android)
     *
     * @param operationSys 系统类型(1ios，2android)
     */
    public void setOperationSys(Integer operationSys) {
        this.operationSys = operationSys;
    }
}