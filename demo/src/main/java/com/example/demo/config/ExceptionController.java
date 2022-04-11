package com.example.demo.config;

import com.example.demo.common.result.Result;
import com.example.demo.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;


//也叫 ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(Throwable.class)
    public Result<Exception> exceptionHanler(Exception e, HttpServletRequest req){
        log.info("URL:{},系统异常:",req.getRequestURI(),e);
        log.info("URI:{},系统异常:",req.getRequestURL(),e);
        return Result.fail(ResultEnum.SYSTEM_ERROR.getCode( ),e.toString());
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public Result exceptionHandler(DuplicateKeyException e,HttpServletRequest req){
        log.info("URL:{},完整性异常:",req.getRequestURI(),e);

        if(req.getRequestURI().toString().startsWith("/ad/insertUser")){
            return Result.fail("插入失败，存在同名用户");
        }
        else if(req.getRequestURI().startsWith("/enter/register")){

            return Result.fail("注册失败，存在同名用户");
        }
        //暂时交给前端去解决
        return Result.fail("完整性异常：可能为用户名冲突"+e.toString());
    }
}
