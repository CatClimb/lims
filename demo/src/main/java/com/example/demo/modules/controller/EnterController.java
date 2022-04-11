package com.example.demo.modules.controller;

import com.example.demo.common.result.Result;
import com.example.demo.dto.enter.LoginDTO;
import com.example.demo.dto.enter.TokenDTO;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.modules.service.EnterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enter")
public class EnterController {

    private final EnterService enterService;
    @Autowired
    public EnterController(EnterService enterService){
        this.enterService=enterService;
    }
    /**
     * 功能1：所有用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @GetMapping
    public Result<TokenDTO> userLogin(@RequestBody LoginDTO loginDTO){
        TokenDTO tokenDTO = enterService.loginGetToken(loginDTO);
        if(/*1.产生token*/tokenDTO!=null){
            //2.成功后返回token
            return Result.success("登录成功",tokenDTO);
        }
        //3.不成功返回错误信息
        return Result.fail("用户名或密码错误");
    }
    /**
     * 功能2：用户注册用户
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @PostMapping
    public Result<TokenDTO> userRegister(@RequestBody UserEntity userEntity){
        TokenDTO tokenDTO = enterService.registerGetToken(userEntity);
        if(/*1.产生token*/tokenDTO!=null){
            //2.成功后返回token
            return Result.success("注册成功",tokenDTO);
        }
        //3.不成功返回错误信息
        return Result.fail("注册失败");
    }
    
}
