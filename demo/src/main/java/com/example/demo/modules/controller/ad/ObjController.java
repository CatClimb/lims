package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.modules.service.ObjService;
import com.example.demo.modules.service.ObjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class ObjController {
    public final ObjService objService;
    @Autowired
    public ObjController(ObjService objService) {
        this.objService = objService;
    }
    @PostMapping("/insertObj")
    private Result<String> insertObj(@RequestBody ObjEntity objEntity){

        boolean b = objService.insert(objEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateObj")
    private Result<String> updateObj(@RequestBody ObjEntity objEntity){
        boolean b = objService.update(objEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryObjTable")
    private Result<ObjEntity> queryObjTable(@RequestBody ObjEntity objEntity){
        log.info("objEntity.toString():"+objEntity.toString());
        objService.setTable(objEntity);
        return Result.success("查询成功",objEntity);
    }

    @PostMapping("/deleteObj/{id}")
    private Result<ObjEntity> deleteObjById(@PathVariable("id") Integer id){
        boolean b = objService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
}
