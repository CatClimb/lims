package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.dto.user.UpPwdDTO;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.modules.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/noad")
//http://localhost:8888/ad/
public class UserController_nd {

    private final UserService userService;
    @Autowired
    public UserController_nd(UserService userService){
        this.userService=userService;
    }


    /**
     * 更新用户
     * @param userEntity
     * @return
     */
    @PostMapping("/updateUser")
    public Result<String> updateUser(@RequestBody UserEntity userEntity){
        boolean b = userService.update(userEntity);
        if(b){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    /**
     * 获取自己信息
     */
    @GetMapping("/personInfo")
    public Result<UserEntity> personInfo(){
        String token = ThreadTmp.getThreadLocalForToken( );
        String userName = TokenUtil.getUserNameByToken(token);
        log.info("userName:"+userName);
        log.info("userService.findUserByUserName(userName):"+userService.findUserByUserName(userName));
        return Result.success("获取个人信息成功",userService.findUserByUserName(userName));
    }

    @PostMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody UpPwdDTO upPwdDTO){
        boolean b = userService.updatePwd(upPwdDTO);
        if(b){
            return Result.success("更新成功");
        }
        return Result.fail("密码错误，请重新输入");
    }
}
