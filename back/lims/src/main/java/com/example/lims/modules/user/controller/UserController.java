package com.example.lims.modules.user.controller;

import com.example.lims.common.annotation.Alias;
import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.common.util.HttpContextUtil;
import com.example.lims.common.util.TokenUtil;
import com.example.lims.dto.*;
import com.example.lims.modules.lab.entity.LabGdtEntity;
import com.example.lims.modules.lab.service.LabGdtService;
import com.example.lims.modules.user.entity.UserEntity;
import com.example.lims.modules.user.service.UserService;

import com.example.lims.vo.LabGdtTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    private String yan="user123434";

    @Autowired
    public UserController(UserService userService, LabGdtService labGdtService) {
        this.userService = userService;
        this.labGdtService = labGdtService;
    }



    //登录
    @RequestMapping("/login")
    public Result login(/*@Valid*/ @RequestBody LoginDTO loginDTO){
//        获取loginVO用户名 并查找数据库
        UserEntity userEntity = userService.findByUserName(loginDTO.getUserName());
        if(userEntity==null){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
        }
        String str = DigestUtils.md5DigestAsHex((loginDTO.getPassword() + yan).getBytes());


        if(!str.equals(userEntity.getPassword())){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "用户名或密码错误");
        }
//        if(!userEntity.getAuth().equals("普通用户")){
//            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"用户名或密码错误");
//        }
        String token= TokenUtil.createJWTToken(userEntity);
        return Result.success(ResultEnum.SUCCESS.getCode(),"登录成功",new TokenDTO(token));
    }
    //注册
    @RequestMapping("/register")
    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,readOnly=false)
    public Result register(@Valid @RequestBody RegisterDTO registerVO) {
        int i=0;
        try {
            String str = DigestUtils.md5DigestAsHex((registerVO.getPassword() + yan).getBytes());
            registerVO.setPassword(str);
            i = userService.insertUser(registerVO);
//            int ia=1/0;
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
    //验证
    @RequestMapping("/verifyToken")
    public Result verifyToken(){
        return Result.success(ResultEnum.SUCCESS.getCode(), "验证成功",null);
    }



    //个人信息
    //查询
    @RequestMapping("/byUserName")
    public Result getUserInfo(){
        String domain = HttpContextUtil.getDomain();
        System.out.println(domain);
        String authorization = HttpContextUtil.getHttpServletRequest().getHeader("Authorization");
        String userName = TokenUtil.getUserNameByToken(authorization);
        return Result.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), userService.findByUserName(userName));
    }
    //更新个人资料
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody UserEntity userEntity){

        int i = userService.updateUser(userEntity);
        if(i<=0){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"更新失败" );
        }
        return Result.success(ResultEnum.SUCCESS.getCode(),"更新成功" , null);
    }
    //更新密码
    @RequestMapping("/updatePwd")
    public Result updatePwd(@RequestBody UpdatePwdDTO updatePwdDTO){
        UserEntity userEntity = userService.findById(updatePwdDTO.getId());
        String str = DigestUtils.md5DigestAsHex((updatePwdDTO.getPassword() + yan).getBytes());
        if(!str.equals(userEntity.getPassword())){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(), "密码错误");
        }
        updatePwdDTO.setPasswordN(DigestUtils.md5DigestAsHex((updatePwdDTO.getPasswordN() + yan).getBytes()));
        int i = userService.updatePwdById(updatePwdDTO);
        if(i<=0){
            return Result.fail(ResultEnum.DATEBASE_CONDITION_ERROR.getCode(),"修改失败" );
        }
        return Result.success(ResultEnum.SUCCESS.getCode(),"修改成功" , null);
    }

    //2 实验室时间管理
    private LabGdtService labGdtService;
    //查询表预约表
    @RequestMapping("/queryLabGdtTable")
    public Result queryLabGdtTable(@RequestBody LabGdtTableVO labGdtTableVO){
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<String[]> strings = new ArrayList<>();
        if(labGdtTableVO.getPage()<=0){
            labGdtTableVO.setPage(1);
        }
        if (labGdtTableVO.getPageSize()<=0){
            labGdtTableVO.setPage(10);
        }
        labGdtTableVO.setStart((labGdtTableVO.getPage()-1)* labGdtTableVO.getPageSize());
        labGdtTableVO.setTableData(labGdtService.queryTable(labGdtTableVO));
        labGdtTableVO.setCount(labGdtService.queryCount(labGdtTableVO));
        Class<LabGdtEntity> labGdtEntityClass = LabGdtEntity.class;
        Field[] declaredFields = labGdtEntityClass.getDeclaredFields();
        int lenght = declaredFields.length;
        for (int i = 0; i < lenght; i++) {
            String[] str = new String[2];
            str[0] = declaredFields[i].getName();
            str[1] = declaredFields[i].getAnnotation(Alias.class).value();
            strings.add(str);
        }
        labGdtTableVO.setTableHead(strings);
        return Result.success(ResultEnum.SUCCESS.getCode(), "", labGdtTableVO);
    }

}
