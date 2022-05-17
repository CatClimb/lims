package com.example.demo.config.interceptor;

import com.example.demo.common.result.InterceptorReturn;
import com.example.demo.common.result.ResultEnum;
import com.example.demo.common.util.HttpContextUtil;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = TokenUtil.getToken(request);
        if (token==null) {
            InterceptorReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "用户未登录，请先登录");
            return false;
        }
        ThreadTmp.setThreadLocalForToken(token);
        if(!TokenUtil.vertifyTokenByExp(token)){//这里就不交由全局异常处理了 麻烦
            InterceptorReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "验证失效，请重新登录");
            return false;
        }

        log.info("HttpContextUtil.getURI():"+HttpContextUtil.getURI().startsWith("/ad"));
        String Auth = TokenUtil.getAuthByToken(token);
        log.info("Auth:"+token);
        log.info("Auth:"+Auth);
        if(HttpContextUtil.getURI().startsWith("/ad")&&Auth.equals("管理员")){
            return true;
        }
        else if(HttpContextUtil.getURI().startsWith("/noad")&&Auth.equals("普通用户")){
            return true;
        }
        else{
            InterceptorReturn.setReturn(response, ResultEnum.FILTER_CONDITION_ERROR.getCode(), "没有权限");
            return false;
        }

    }
}
