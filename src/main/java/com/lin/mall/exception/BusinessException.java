package com.lin.mall.exception;

import com.lin.mall.enums.ResponseStatusEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(ResponseStatusEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.code = responseStatusEnum.getCode();
    }

}
