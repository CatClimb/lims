package com.example.lims.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//
@RestControllerAdvice
@Slf4j
public class MyControllerAdvice {
    //全局异常处理
    @ExceptionHandler(Throwable.class)
    public Result ExceptionHandler(Exception e, HttpServletRequest req){
        Result fail = Result.fail(ResultEnum.SYSTEM_ERROR.getCode(), e.toString());
        log.info("URL:{},系统异常:",req.getRequestURI(),e);
        log.info("URL:{},系统异常:",req.getRequestURL(),e);
        return fail;
    }

    @ExceptionHandler(JWTVerificationException.class)
    public Result ExceptionHandler(JWTVerificationException e, HttpServletRequest req){
        Result fail = Result.fail(ResultEnum.SYSTEM_ERROR.getCode(), e.toString());
        log.info("URL:{},系统异常1:",req.getRequestURI(),e);
        log.info("URL:{},系统异常1:",req.getRequestURL(),e);
        return fail;
    }


//    @ExceptionHandler(RuntimeException.class)
//
//    public Result RuntimeExceptionHandler(Exception e, HttpServletRequest req){
//        Result fail = Result.fail(ResultEnum.SYSTEM_ERROR.getCode(), "系统异常，操作失败");
//        log.info("URL:{},系统异常:",req.getRequestURI(),e);
//        log.info("URL:{},系统异常:",req.getRequestURL(),e);
//        return fail;
//    }
    //BindException处理  处理UserEntity notBlank等注解信息
//    @ExceptionHandler(BindException.class)
//    public Result BindExceptionHandler(BindException be, HttpServletRequest req){
//        String failMsg=be.getBindingResult().getFieldError().getDefaultMessage();
//        Result fail = Result.fail(ResultEnum.INVALID_FORMAT.getCode(), failMsg);
//        log.info("URL:{},绑定异常:",req.getRequestURI(), be);
//        return fail;
//    }

}
