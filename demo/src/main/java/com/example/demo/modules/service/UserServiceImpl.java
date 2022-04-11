package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.UserDao;
import com.example.demo.modules.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private TableControlUtil<UserEntity> tableHeadUtil;
    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }
    @Autowired
    public void setTableHeadUtil(TableControlUtil<UserEntity> tableHeadUtil){
        this.tableHeadUtil=tableHeadUtil;
    }

    @Override
    public boolean insertUser(UserEntity userEntity) {
        return userDao.insertUser(userEntity);
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        return userDao.updateUser(userEntity);
    }

    @Override
    public void queryUserTable(UserEntity userEntity) {
        tableHeadUtil.setTable(userEntity,userDao);
        userEntity.setTableHead(tableHeadUtil.getTableHead(UserEntity.class));
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public UserEntity findUserByUserName(String UserName) {
        return userDao.findUserByUserName(UserName);
    }
}
