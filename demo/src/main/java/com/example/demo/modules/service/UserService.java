package com.example.demo.modules.service;

import com.example.demo.dto.user.UpPwdDTO;
import com.example.demo.modules.entity.UserEntity;

public interface UserService extends Service{

    public UserEntity findUserByUserName(String userName);
    public boolean updatePwd(UpPwdDTO upPwdDTO);
}
