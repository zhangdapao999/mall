package com.lin.mall.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    NORMAL(0, "正常"),
    FROZEN(1, "冻结"),
    ;

    final Integer code;
    final String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
