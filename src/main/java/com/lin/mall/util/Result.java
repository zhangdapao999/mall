package com.lin.mall.util;

import lombok.Data;

@Data
public class Result<T> {
    private int code; // 状态码
    private String message; // 消息
    private T data; // 数据

    // 构造函数
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应的静态方法
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(200, message, data);
    }

    // 错误响应的静态方法
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

}