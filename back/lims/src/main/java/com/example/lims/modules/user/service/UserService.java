package com.example.lims.modules.user.service;

import com.example.lims.common.result.Result;
import com.example.lims.dto.UpdatePwdDTO;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.dto.RegisterDTO;

public interface UserService {
    public UserEntity findByUserName(String userName);
    public int insertUser(RegisterDTO registerVO);

    public int updateUser(UserEntity userEntity);
    public UserEntity findById(int id);
    public int updatePwdById(UpdatePwdDTO updatePwdDTO);
}
