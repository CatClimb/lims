package com.example.demo.modules.dao;

import com.example.demo.modules.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends Dao {
    public UserEntity findUserByUserName(String userName);
}
