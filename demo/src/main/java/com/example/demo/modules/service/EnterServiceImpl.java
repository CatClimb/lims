package com.example.demo.modules.service;

import com.example.demo.common.util.TokenUtil;
import com.example.demo.dto.enter.LoginDTO;
import com.example.demo.dto.enter.RegisterDTO;
import com.example.demo.dto.enter.TokenDTO;
import com.example.demo.modules.dao.UserDao;
import com.example.demo.modules.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
@Service
@Slf4j
public class EnterServiceImpl implements EnterService {
    @Autowired
    private final UserDao userDao;
    private final String salt ="user123434";
    public EnterServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public TokenDTO loginGetToken(LoginDTO loginDTO) {
//      1.找出用户
        UserEntity userEntity= userDao.findUserByUserName(loginDTO.getUserName());
        if(userEntity!=null){
//            2.判断密码是否正确返回token
            if( DigestUtils.md5DigestAsHex((loginDTO.getPassword() + salt).getBytes()).equals(userEntity.getPassword())){
                String token= TokenUtil.createJWTToken(userEntity.getUserName(),userEntity.getAuth());
                return new TokenDTO(token);
            }
        }
//        3.返回空
        return null;
    }
    @Override
    public TokenDTO registerGetToken(UserEntity userEntity) {

        String pwd = DigestUtils.md5DigestAsHex((userEntity.getPassword( ) + salt).getBytes());
        userEntity.setPassword(pwd);
        boolean b = userDao.insert(userEntity);

        if(b){
//            2.插入成功返回token
            String token= TokenUtil.createJWTToken(userEntity.getUserName(),userEntity.getAuth());
            return new TokenDTO(token);
        }
        //        3.返回空
        return null;
    }
}
