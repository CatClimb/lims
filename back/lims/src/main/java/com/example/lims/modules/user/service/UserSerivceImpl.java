package com.example.lims.modules.user.service;

import com.example.lims.common.result.Result;
import com.example.lims.common.util.HttpContextUtil;
import com.example.lims.common.util.TokenUtil;
import com.example.lims.dto.UpdatePwdDTO;
import com.example.lims.modules.user.dao.UserDao;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSerivceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserSerivceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //返回user对象
    public UserEntity findByUserName(String userName){
        return userDao.findByUserName(userName);
    }
    //插入user对象
    public int insertUser(RegisterDTO registerVO){
        return userDao.insertUser(registerVO);
    }
    //


    public int updateUser(UserEntity userEntity){
        return userDao.updateUser(userEntity);

    }


    public UserEntity findById(int id) {
       return userDao.findById(id);
    }

    public int updatePwdById(UpdatePwdDTO updatePwdDTO){
       return userDao.updatePwdById(updatePwdDTO);
    }
}
