package com.example.lims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages ={ "com.example.lims.modules.*.dao"})
@EnableTransactionManagement
public class LimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimsApplication.class, args);
	}

}
