package com.example.lims.modules.user.service;

import com.example.lims.modules.user.dao.UserDao;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerivceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserSerivceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserEntity findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public int insertUser(RegisterVO registerVO){
        return userDao.insertUser(registerVO);
    }
}
