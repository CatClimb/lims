package com.example.demo.common.result;


import com.alibaba.fastjson.JSON;
import com.example.demo.common.util.HttpContextUtil;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//通过不使用dispatcherServlet进行返回
public class InterceptorReturn {
    public static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        response.setContentType("text/html; charset=UTF-8");
        //        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(400);
        response.setContentType("application/json;charset=utf-8");
        Result fail = Result.fail(status, msg);
        String json = JSON.toJSONString(fail);
        httpResponse.getWriter().print(json);
    }
}
