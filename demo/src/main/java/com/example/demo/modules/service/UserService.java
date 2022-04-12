package com.example.demo.modules.service;

import com.example.demo.modules.entity.UserEntity;

public interface UserService {
    public boolean insertUser(UserEntity userEntity);
    public boolean updateUser(UserEntity userEntity);
    public void queryUserTable(UserEntity userEntity);
    public boolean deleteUserById(Integer id);
    public UserEntity findUserByUserName(String userName);
}
