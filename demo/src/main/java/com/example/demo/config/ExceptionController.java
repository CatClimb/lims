package com.example.demo.config;

import com.example.demo.common.result.Result;
import com.example.demo.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import org.springframework.dao.DataIntegrityViolationException;
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
    @ExceptionHandler({DuplicateKeyException.class})
    public Result exceptionHandler(DuplicateKeyException e,HttpServletRequest req){
        log.info("URL:{},完整性异常:",req.getRequestURI(),e);

        if(req.getRequestURI().toString().startsWith("/ad/insertUser")){
            return Result.fail("插入失败，存在同名用户");
        }

        else if(req.getRequestURI().startsWith("/enter/register")){

            return Result.fail("注册失败，存在同名用户");
        }
        else if(req.getRequestURI().startsWith("/ad/updateUser")){

            return Result.fail("修改失败，存在同名用户");
        }
        else if(req.getRequestURI().startsWith("/ad/insertUser")){
            return Result.fail("插入失败，存在同名用户");

        }
        else if(req.getRequestURI().startsWith("/ad/updateLabGdt")||req.getRequestURI().startsWith("/ad/insertLabGdt")){

            return Result.fail("存在相同数据，请重新操作");
        }

        //暂时交给前端去解决
        return Result.fail("完整性异常：可能为用户名冲突"+e.toString());
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public Result exceptionHandler(DataIntegrityViolationException e,HttpServletRequest req){
        log.info("URL:{},完整性异常:",req.getRequestURI(),e);

        if(req.getRequestURI().toString().startsWith("/ad/deleteLab")){
            return Result.fail("关联的预约信息未删除");
        }
        else if(req.getRequestURI().startsWith("/ad/insertLabGdt")){

            return Result.fail("请关联实验室编号");
        }
        else if(req.getRequestURI().startsWith("/ad/updateLab")){

            return Result.fail("关联异常，请删除掉关联实验室的数据");
        }

        //暂时交给前端去解决
        return Result.fail("完整性异常：可能为用户名冲突2"+e.toString());
    }


}
