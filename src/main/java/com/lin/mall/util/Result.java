package com.lin.mall.util;

import com.lin.mall.enums.ResponseStatusEnum;
import lombok.Data;

@Data
public class Result<T> {
    private int code; // 状态码
    private T data; // 数据
    private String message; // 消息

    // 构造函数
    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // 构造函数
    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    // 构造函数
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 成功响应的静态方法
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(200, data, message);
    }

    // 成功响应的静态方法
    public static <T> Result<T> success(T data) {
        return new Result<>(200, data);
    }

    // 错误响应的静态方法
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, null, message);
    }

    // 错误响应的静态方法
    public static <T> Result<T> error(ResponseStatusEnum responseStatusEnum) {
        return new Result<>(responseStatusEnum.getCode(), responseStatusEnum.getMessage());
    }

    // 错误响应的静态方法
    public static <T> Result<T> ok() {
        return new Result<>(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage());
    }

}