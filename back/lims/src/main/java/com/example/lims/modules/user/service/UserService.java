package com.example.lims.modules.user.service;

import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.vo.RegisterVO;

public interface UserService {
    public UserEntity findByUserName(String userName);
    public int insertUser(RegisterVO registerVO);
}
