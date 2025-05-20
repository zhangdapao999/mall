package com.lin.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "订单表")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

    @ApiModelProperty("0:未付款 1:已付款 2:退款中 3:已退款")
    private Integer status;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "id = " + id +
            ", orderSn = " + orderSn +
            ", userId = " + userId +
            ", totalPrice = " + totalPrice +
            ", status = " + status +
            ", createdTime = " + createdTime +
            ", payTime = " + payTime +
        "}";
    }
}
