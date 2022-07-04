package com.example.demo.modules.dao;

import com.example.demo.dto.user_role.URDTO;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.entity.RoleEntity;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao extends Dao {
    public UserEntity findUserByUserName(String userName);
    public List<UserEntity> mulTableQueryRole(TableVO tableVO);
    public Integer mulTableQueryRoleCount(TableVO tableVO);

    //UR
    public Integer mulAddMiddleTableRole(List<URDTO> urdtos);
    public Integer mulDeleteMiddleTableRole(List<URDTO> urdtos);
    public UserEntity getRoleByUserId(Integer id);
}
