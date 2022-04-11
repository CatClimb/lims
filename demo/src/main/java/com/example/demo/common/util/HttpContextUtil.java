package com.example.demo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class HttpContextUtil {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        log.info("URL:"+request.getRequestURL());
        log.info("URI:"+request.getRequestURI());
        log.info("getDomain()："+url.delete(url.length() - request.getRequestURI().length(), url.length()).toString());
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }
    public static String getURI(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getRequestURI();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

}
