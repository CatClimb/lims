package com.example.demo.modules.service;

import com.example.demo.dto.user.UpPwdDTO;
import com.example.demo.dto.user_role.URDTO;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface UserService extends Service{

    public UserEntity findUserByUserName(String userName);
    public boolean updatePwd(UpPwdDTO upPwdDTO);
    public void mulTableQueryRole(TableVO tableVO);
    public Integer mulAddMiddleTableRole(List<URDTO> urdtos);
    public Integer mulDeleteMiddleTableRole(List<URDTO> urdtos);
    public UserEntity getRoleByUserId(Integer id);
}
