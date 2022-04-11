package com.example.demo.modules.service;

import com.example.demo.dto.enter.LoginDTO;
import com.example.demo.dto.enter.RegisterDTO;
import com.example.demo.dto.enter.TokenDTO;
import com.example.demo.modules.entity.UserEntity;

public interface EnterService {
    //1.登录用户信息并产生token
    public TokenDTO loginGetToken(LoginDTO loginDTO);
    //2.注册用户信息并产生token
    public TokenDTO registerGetToken(UserEntity userEntity);

}

