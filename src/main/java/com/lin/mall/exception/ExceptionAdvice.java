package com.lin.mall.exception;

import com.lin.mall.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * @param  e 抛出的异常
     * @return 业务异常处理的信息
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        //记录日志
        log.error(e.getMessage(), e);
        //返回错误信息
        return Result.error(e.getCode(), e.getMessage());

    }
}
