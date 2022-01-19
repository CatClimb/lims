package com.example.lims.modules.user.controller;

import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.common.util.TokenUtil;
import com.example.lims.dto.TokenDTO;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.modules.user.service.UserService;
import com.example.lims.dto.LoginDTO;
import com.example.lims.dto.RegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/user")

public class UserController {
    private final UserService userService;
    private String yan="user123434";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //登录
    @RequestMapping("/login")
    public Result login(/*@Valid*/ @RequestBody LoginDTO loginDTO){
        //获取loginVO用户名 并查找数据库
        UserEntity userEntity = userService.findByUserName(loginDTO.getUserName());
        if(userEntity==null){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }
        String str = DigestUtils.md5DigestAsHex((loginDTO.getPassword() + yan).getBytes());


        if(!str.equals(userEntity.getPassword())){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名或密码错误");
        }
        if(!userEntity.getAuth().equals("普通用户")){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }
        String token= TokenUtil.createJWTToken(userEntity);
        return Result.success(ResultEnum.SUCCESS.getCode(),"登录成功",new TokenDTO(token));
    }
    //注册
    @RequestMapping("/register")
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly=false)
    public Result register(@Valid @RequestBody RegisterDTO registerVO) {
        int i=0;
        try {

            String password = registerVO.getPassword();
            String str = DigestUtils.md5DigestAsHex((registerVO.getPassword() + yan).getBytes());
            registerVO.setPassword(str);
            i = userService.insertUser(registerVO);
            int ia=1/0;
        } catch (Exception e) {
            throw new RuntimeException();
        }
        if (i <= 0) {
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名重名");
        } else {
            UserEntity userEntity = userService.findByUserName(registerVO.getUserName());
            String token = TokenUtil.createJWTToken(userEntity);
            return Result.success(ResultEnum.SUCCESS.getCode(), "注册成功", token);
        }
    }
    //注销
    @RequestMapping("/logout")
    public Result logout(/*@Valid*/ @RequestBody LoginDTO loginDTO){
        return new Result();
    }

    @RequestMapping("/verifyToken")
    public Result verifyToken(){
        return Result.success(ResultEnum.SUCCESS.getCode(), "验证成功",null);
    }


    @RequestMapping("/byUserName")
    public Result getUserInfo(){
        return Result.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), userService.selectUserByUserName());
    }

    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody UserEntity userEntity){

        int i = userService.updateUser(userEntity);
        if(i<=0){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"更新失败" );
        }
        return Result.success(ResultEnum.SUCCESS.getCode(),"更新成功" , null);
    }
    @RequestMapping("/updatePwd")
    public Result updateUser()
}
