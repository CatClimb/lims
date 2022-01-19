package com.example.lims.modules.user.dao;

import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.dto.RegisterDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public UserEntity findByUserName(String userName);
    public int insertUser(RegisterDTO registerVO);
    public UserEntity selectUserByUserName(String userName);
    public int updateUser(UserEntity userEntity);

}
