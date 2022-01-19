package com.example.lims.modules.user.service;

import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.dto.RegisterDTO;

public interface UserService {
    public UserEntity findByUserName(String userName);
    public int insertUser(RegisterDTO registerVO);
    public UserEntity selectUserByUserName();
    public int updateUser(UserEntity userEntity);
}
