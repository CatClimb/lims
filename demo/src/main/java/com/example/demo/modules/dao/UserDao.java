package com.example.demo.modules.dao;

import com.example.demo.modules.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends Dao {
    //1.插入用户信息
    public boolean insertUser(UserEntity userEntity);
    //2.更新用户信息
    public boolean updateUser(UserEntity userEntity);
    //3.查询所有用户信息
//    public List<TableVO> queryTable(TableVO tableVO);
    //4.删除用户信息
    public boolean deleteUserById(Integer id);
    //5.获取个人用户信息
    public UserEntity findUserByUserName(String UserName);
    //6.查询用户数量
//    public Integer queryTableCount(TableVO tableVO);

}
