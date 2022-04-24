package com.example.demo;

import com.example.demo.dto.sme_inc.QueryConditionalForSI;
import com.example.demo.modules.dao.ComsumeDao;
import com.example.demo.modules.entity.ComsumeEntity;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@MapperScan(basePackages={"com.example.demo.modules.dao"})
@EnableTransactionManagement
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
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
