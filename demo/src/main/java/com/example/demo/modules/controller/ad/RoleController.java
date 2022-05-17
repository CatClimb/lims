package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.RoleEntity;
import com.example.demo.modules.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
public class RoleController {
    public final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/insertRole")
    private Result<String> insertRole(@RequestBody RoleEntity roleEntity){

        boolean b = roleService.insert(roleEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateRole")
    private Result<String> updateRole(@RequestBody RoleEntity roleEntity){
        boolean b = roleService.update(roleEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryRoleTable")
    private Result<RoleEntity> queryRoleTable(@RequestBody RoleEntity roleEntity){

        roleService.setTable(roleEntity);
        return Result.success("查询成功",roleEntity);
    }

    @PostMapping("/deleteRole/{id}")
    private Result<RoleEntity> deleteRoleById(@PathVariable("id") Integer id){
        boolean b = roleService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
}
