package com.example.lims.modules;

import com.example.lims.common.enums.ResultEnum;
import com.example.lims.common.result.Result;
import com.example.lims.modules.user.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class TestController {
    @RequestMapping("/login")
    public Result<Object> login(@Valid @RequestBody UserEntity userEntity){
        return Result.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }
    @RequestMapping("/a")
    public String user(){
        return "123";
    }
    @RequestMapping("/user/user")
    public String s(){
        return "3";
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(new Date(1642301132));
    }
}
