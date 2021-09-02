package com.crm.common.util.exception;

import com.crm.common.util.result.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @RestControllerAdvice 该注解进行全局异常的捕获
 * @log4j2 进行日志记录
 */

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    //实体校验异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)             //返回状态码
    @ExceptionHandler(value = MethodArgumentNotValidException.class)   //捕获运行时异常
    public Result handle(MethodArgumentNotValidException e){

        //日志打印错误信息
        log.error("实体校验异常----------{}"+e);

        //对错误抛出进行简单处理
        BindingResult bindingResult = e.getBindingResult();
        //仅抛出 众多错误中的其中一个(这样，错误信息更简洁)
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        //返回失败的结果集
        return Result.fail(400,objectError.getDefaultMessage(),null);
    }


    //业务异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)             //返回状态码
    @ExceptionHandler(value = RuntimeException.class)   //捕获运行时异常
    public Result handle(RuntimeException e){

        //日志打印错误信息
        log.error("运行时异常----------{}"+e);
        //返回失败的结果集
        return Result.fail(401,e.getMessage(),null);
    }

    //用户不存在异常处理
    @ResponseStatus(HttpStatus.BAD_REQUEST)             //返回状态码
    @ExceptionHandler(value = IllegalArgumentException.class)   //捕获运行时异常
    public Result handle(IllegalArgumentException e){

        //日志打印错误信息
        log.error("Assert断言异常--AccountController--------{}"+e);
        //返回失败的结果集
        return Result.fail(401,e.getMessage(),null);
    }
}
