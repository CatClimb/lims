package com.example.lims.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http与https都行
@Configuration
public class TomcatHttpConfig {
    @Value("${server.port}")
    private int sslPort;
    private int httpPort=8888;

    @Bean
    public TomcatServletWebServerFactory servletcontainerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(initHttpConnector());
        return tomcatServletWebServerFactory;
    }

    private Connector initHttpConnector(){
        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(httpPort);
        return connector;
    }
}



////所有 http转https
//@Configuration
//public class TomcatHttpConfig {
//    @Value("${server.port}")
//    private int sslPort;
//    private int httpPort=8081;
//
//    @Bean
//    public TomcatServletWebServerFactory servletcontainerFactory(){
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory()
//        {
//            @Override
//            protected void postProcessContext(Context context) {
//                //设置安全性约束
//                SecurityConstraint securityConstraint=new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                //设置约束条件
//                SecurityCollection securityCollection=new SecurityCollection();
//                //这个是拦截servlet映射的
//                //拦截所有servlet
//                securityCollection.addPattern("/*");
//                securityConstraint.addCollection(securityCollection);
//                context.addConstraint(securityConstraint);
//
//
//
//            }
//        };
//
//        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(initHttpConnector());
//        return tomcatServletWebServerFactory;
//    }
//
//    private Connector initHttpConnector(){
//        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//
//        connector.setPort(httpPort);
//        //true： http使用http, https使用https;
//        //false： http重定向到https;
//        connector.setSecure(false);
//        connector.setRedirectPort(sslPort);
//        return connector;
//    }
//}
