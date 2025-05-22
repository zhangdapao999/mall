package com.lin.mall.enums;

import lombok.Getter;

@Getter
public enum DeleteEnum {

    NOT_DELETE(0, "正常"),
    IS_DELETED(1, "已删除"),
    ;

    final Integer code;
    final String message;

    DeleteEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
