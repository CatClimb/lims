package com.example.lims.modules.user.service;

import com.example.lims.common.util.HttpContextUtil;
import com.example.lims.common.util.TokenUtil;
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

    public UserEntity findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public int insertUser(RegisterDTO registerVO){
        return userDao.insertUser(registerVO);
    }
    public UserEntity selectUserByUserName(){
        String domain = HttpContextUtil.getDomain();
        System.out.println(domain);
        String authorization = HttpContextUtil.getHttpServletRequest().getHeader("Authorization");
        String userName = TokenUtil.getUserNameByToken(authorization);
        return userDao.selectUserByUserName(userName);
    }
    public int updateUser(UserEntity userEntity){
        return userDao.updateUser(userEntity);

    }
}
