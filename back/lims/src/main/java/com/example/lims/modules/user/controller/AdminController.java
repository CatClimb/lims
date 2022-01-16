package com.example.lims.modules.user.controller;

import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.common.util.TokenUtil;
import com.example.lims.dto.TokenVO;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.modules.user.service.UserService;
import com.example.lims.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    AdminController(UserService userService){
        this.userService=userService;
    }
    @RequestMapping("/login")
    public Result login(/*@Valid*/ @RequestBody LoginVO loginVO) {
        //获取loginVO用户名 并查找数据库
        UserEntity userEntity = userService.findByUserName(loginVO.getUserName());
        if (userEntity == null) {
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名或密码错误");
        }

        if (!loginVO.getPassword().equals(userEntity.getPassword())) {
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名或密码错误");
        }
        //用户权限验证
        if(!userEntity.getAuth().equals("管理员")){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }
        String token = TokenUtil.createJWTToken(userEntity);
        return Result.success(ResultEnum.SUCCESS.getCode(), "登录成功", new TokenVO(token));
    }
    @RequestMapping("/logout")
    public Result logout(/*@Valid*/ @RequestBody LoginVO loginVO){
        return new Result();
    }
    @RequestMapping("/verifyToken")
    public Result verifyToken(){
        return Result.success(ResultEnum.SUCCESS.getCode(), "验证成功",null);
    }
}
