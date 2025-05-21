package com.lin.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    SUCCESS(200, "操作成功"),

    USERNAME_EXIST(301, "用户名已存在"),
    PASSWORD_NOT_SAME(302, "两次密码不同"),

    // 请求参数代码
    PARAM_MISSING(4001, "缺少必要的请求参数"),
    ;

    final Integer code;
    final String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
