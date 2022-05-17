package com.example.demo;

import com.example.demo.modules.dao.ConsumeDao;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.service.ConsumeService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages={"com.example.demo.modules.dao"})
@EnableTransactionManagement
public class DemoApplication {



    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
//        ConsumeService consumeService = (ConsumeService) run.getBean("consumeService");
//        if(consumeService!=null){
//            System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddd" );
//        }
//        String[] beanDefinitionNames = run.getBeanDefinitionNames( );
//        for (String n :
//                beanDefinitionNames) {
//            System.out.println(n);
//        }
//
//
//        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) run.getBean("sqlSessionFactory");
//
//        SqlSession sqlSession = sqlSessionFactory.openSession( );
//        ConsumeDao mapper = sqlSession.getMapper(ConsumeDao.class);
//
//        SqlSession sqlSession2 = sqlSessionFactory.openSession( );
//        ConsumeDao mapper2 = sqlSession2.getMapper(ConsumeDao.class);
//
//        ConsumeEntity consumeEntity = mapper.selectById(1);
//        System.out.println(consumeEntity);
//        sqlSession.close();
//
//        ConsumeEntity consumeEntity2 = mapper2.selectById(1);
//        System.out.println(consumeEntity2);
//
//
//        System.out.println(consumeEntity==consumeEntity2);
//        sqlSession2.close();
    }
}