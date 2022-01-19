package com.example.lims.modules.user.controller;

import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.common.util.TokenUtil;
import com.example.lims.dto.TokenDTO;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.modules.user.service.UserService;
import com.example.lims.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superAdmin")
public class SuperAdminController {
    private UserService userService;
    @Autowired
    SuperAdminController(UserService userService){
        this.userService=userService;
    }
    @RequestMapping("/login")
    public Result login(/*@Valid*/ @RequestBody LoginDTO loginDTO){
        //获取loginVO用户名 并查找数据库
        UserEntity userEntity = userService.findByUserName(loginDTO.getUserName());
        if(userEntity==null){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }

        if(!loginDTO.getPassword().equals(userEntity.getPassword())){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名或密码错误");
        }
        //用户权限验证
        if(!userEntity.getAuth().equals("超级管理员")){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }
        String token= TokenUtil.createJWTToken(userEntity);
        return Result.success(ResultEnum.SUCCESS.getCode(),"登录成功",new TokenDTO(token));
    }
    @RequestMapping("/logout")
    public Result logout(/*@Valid*/ @RequestBody LoginDTO loginDTO){
        return new Result();
    }
    @RequestMapping("/verifyToken")
    public Result verifyToken(){
        return Result.success(ResultEnum.SUCCESS.getCode(), "验证成功",null);
    }
}
