package com.example.demo;


import com.example.demo.modules.dao.UserDao;
import com.example.demo.modules.entity.UserEntity;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Consumer;


@SpringBootTest
class DemoApplicationTests {
    @Autowired
    public UserDao userDao;

    @Test
    void contextLoads() {
        UserEntity roleByUserId = userDao.findUserByUserName("飞鹿");
        System.out.println(roleByUserId.toString() );
        List<UserEntity> list =new ArrayList<>(  );
        list.forEach(System.out::println);


    }

}
