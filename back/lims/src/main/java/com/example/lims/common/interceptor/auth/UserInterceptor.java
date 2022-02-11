package com.example.lims.common.interceptor.auth;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.FailReturn;
import com.example.lims.common.result.Result;
import com.example.lims.common.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

public class UserInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String token = TokenUtil.getToken(request);
        if(token==null){
            FailReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "用户未登录，请先登录");
            return false;
        }

        if(!TokenUtil.vertifyTokenByExp(token)){
            FailReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "验证失效，请重新登录");
            return false;
        }
        String Auth = TokenUtil.getAuthByToken(token);
        if(Auth.equals("普通用户")){
            return true;
        }
        FailReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "没有权限");
        return false;
    }
}
