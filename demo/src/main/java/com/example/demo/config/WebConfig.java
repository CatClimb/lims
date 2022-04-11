package com.example.demo.config;


import com.example.demo.config.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)

//代替SpringMVC的配置文件
public class WebConfig implements WebMvcConfigurer {
    private final AuthInterceptor adInterceptor;

    @Autowired
    public WebConfig(AuthInterceptor adInterceptor){
        this.adInterceptor=adInterceptor;

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adInterceptor).addPathPatterns(
                "/ad/**",
                "/noad/**"
        );

    }
}
