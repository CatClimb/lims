package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.dto.user.UpPwdDTO;
import com.example.demo.dto.user_role.ConditionalURDTO;
import com.example.demo.dto.user_role.URDTO;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.modules.service.EnterService;
import com.example.demo.modules.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/ad")
//http://localhost:8888/ad/
public class UserController {
    @Autowired
    private EnterService enterService;
    private final UserService userService;
    @Autowired
    public UserController(UserService userService,EnterService enterService ){
        this.userService=userService;
        this.enterService=enterService;
    }
    /**
     * 添加用户
     * @param userEntity
     * @return
     */
    @PostMapping("/insertUser")
    public Result<String> insertUser(@RequestBody UserEntity userEntity){
        log.info(userEntity.toString());
        boolean b = userService.insert(userEntity);
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
        boolean b = userService.update(userEntity);
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

    @RequestMapping(value = "/queryUserTable",method=RequestMethod.POST)
    public Result<UserEntity> queryUserTable(@RequestBody UserEntity userEntity){
        userService.setTable(userEntity);
        log.info("userEntity:"+userEntity.toString());
        return Result.success("查询成功",userEntity);
    }


    @PostMapping("/deleteUser/{id}")

    public Result<UserEntity> deleteUser( @PathVariable("id") Integer id){
        boolean b = userService.deleteById(id);
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

    @PostMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody UpPwdDTO upPwdDTO){
        boolean b = userService.updatePwd(upPwdDTO);
        if(b){
            return Result.success("更新成功");
        }
        return Result.fail("密码错误，请重新输入");
    }

    @PostMapping("/mtqueryr")
    private Result<ConditionalURDTO> mulTableQueryRole(@RequestBody ConditionalURDTO conditionalUSDTO){
        userService.mulTableQueryRole(conditionalUSDTO);
        return Result.success("查询成功",conditionalUSDTO);
    }
    @RequestMapping("/getRoleByUserId")
    private Result<UserEntity> getRoleByUserId(@RequestParam("id") Integer id){
        UserEntity roleByUserId = userService.getRoleByUserId(id);
        return Result.success("查询成功",roleByUserId);
    }

    @PostMapping("/addUserRole")
    public Result<String> addUserRole(@RequestBody List<URDTO> urdtos){
        Integer b = userService.mulAddMiddleTableRole(urdtos);
        if(b>0){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }



    @PostMapping("/deleteUserRole")
    public Result<String> deleteUserRole(@RequestBody List<URDTO> urdtos){
        Integer b = userService.mulDeleteMiddleTableRole(urdtos);
        if(b>0){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}
