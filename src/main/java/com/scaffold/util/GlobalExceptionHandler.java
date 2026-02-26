package com.scaffold.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理（接单项目必做，避免页面报错）
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        e.printStackTrace(); // 实际项目可接入日志框架
        return Result.error(e.getMessage());
    }
}