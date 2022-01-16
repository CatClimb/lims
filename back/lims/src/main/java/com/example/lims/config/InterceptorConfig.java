package com.example.lims.config;

import com.example.lims.common.interceptor.auth.AdminInterceptor;
import com.example.lims.common.interceptor.auth.SuperAdminInterceptor;
import com.example.lims.common.interceptor.auth.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private SuperAdminInterceptor superAdminInterceptor;
    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns(
                        "/admin/*"
                )
                .excludePathPatterns(
                        "/admin/login",
                        "/admin/logout"
                );
        registry.addInterceptor(superAdminInterceptor)
                .addPathPatterns(
                        "/superAdmin/*"
                )
                .excludePathPatterns(
                        "/superAdmin/login",
                        "/superAdmin/logout"
                );

        registry.addInterceptor(userInterceptor)
                .addPathPatterns(
                        "/user/*"
                )
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/user/logout"
                );


    }
}