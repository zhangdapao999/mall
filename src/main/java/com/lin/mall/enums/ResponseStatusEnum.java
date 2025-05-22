package com.lin.mall.enums;

import lombok.Getter;


@Getter
public enum ResponseStatusEnum {

    SUCCESS(200, "操作成功"),

    USERNAME_EXIST(301, "用户名已存在"),
    PASSWORD_NOT_SAME(302, "两次密码不同"),
    USERNAME_NOT_EXIST(303, "用户不存在"),
    PASSWORD_ERROR(304, "密码错误"),
    USER_FROZEN(305, "用户已被冻结"),
    LOGIN_EXPIRED(306, "登录已过期"),
    INSUFFICIENT_BALANCE(307, "余额不足"),

    // 请求参数代码
    PARAM_MISSING(4001, "缺少必要的请求参数"),

    // 系统错误,
    SYSTEM_ERROR(500, "系统异常,请联系管理员"),

    // 商品
    PRODUCT_NOT_EXIST(6001, "商品不存在"),
    PRODUCT_INSUFFICIENT_INVENTORY(6002, "商品库存不足"),
    ;

    final Integer code;
    final String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
