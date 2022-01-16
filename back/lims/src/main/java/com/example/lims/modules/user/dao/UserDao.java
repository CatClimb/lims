package com.example.lims.modules.user.dao;

import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.vo.RegisterVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public UserEntity findByUserName(String userName);
    public int insertUser(RegisterVO registerVO);
}
