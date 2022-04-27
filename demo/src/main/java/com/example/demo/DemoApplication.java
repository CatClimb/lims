package com.example.demo;

import com.example.demo.modules.service.ConsumeService;
import org.mybatis.spring.annotation.MapperScan;
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
        String[] beanDefinitionNames = run.getBeanDefinitionNames( );
        for (String n :
                beanDefinitionNames) {
            System.out.println(n );
        }


//        SqlSessionFactory sqlSessionFactory =(SqlSessionFactory) run.getBean("sqlSessionFactory");
//        SqlSession sqlSession = sqlSessionFactory.openSession( );
//        ComsumeDao mapper = sqlSession.getMapper(ComsumeDao.class);
//        QueryConditionalForSI queryConditionalForSI = new QueryConditionalForSI( );
//        queryConditionalForSI.setPage(1);
//        queryConditionalForSI.setPageSize(10);
//        List<ComsumeEntity> comsumeEntities = mapper.mulTablequeryInRecord(queryConditionalForSI);
//        for (ComsumeEntity c :
//                comsumeEntities) {
//            System.out.println(c.toString() );
//
//        }


    }
}
