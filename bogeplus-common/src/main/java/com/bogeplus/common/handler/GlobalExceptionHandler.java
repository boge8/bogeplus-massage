package com.bogeplus.common.handler;

import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public Result handleException(BizException e) {
        log.info("业务异常：{}", e);
        return Result.faild(e.getMessage(),e.getCode());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常：{}", e);
        return Result.faild(e.getMessage());
    }
}
