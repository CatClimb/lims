package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.modules.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ad")
//http://localhost:8888/ad/
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    @PostMapping("/insertUser")
    public Result<String> insertUser(@RequestBody UserEntity userEntity){
        log.info(userEntity.toString());
        boolean b = userService.insertUser(userEntity);
        if(b){
          return  Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }

    /**
     * 更新用户
     * @param userEntity
     * @return
     */
    @PostMapping("/updateUser")
    public Result<String> updateUser(@RequestBody UserEntity userEntity){
        boolean b = userService.updateUser(userEntity);
        if(b){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    /**
     *
     * @param userEntity
     * @return
     */

    @RequestMapping(value = "/queryUserTable",method=RequestMethod.GET)
    public Result<UserEntity> queryUserTable(@RequestBody UserEntity userEntity){
        userService.queryUserTable(userEntity);
        return Result.success("查询成功",userEntity);
    }


    @PostMapping("/deleteUser/{id}")

    public Result<UserEntity> deleteUser( @PathVariable("id") Integer id){
        boolean b = userService.deleteUserById(id);
        if(b){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
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
}
