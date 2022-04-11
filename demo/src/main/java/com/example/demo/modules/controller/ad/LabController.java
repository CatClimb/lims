package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
public class LabController {

    public final LabService labService;
    @Autowired
    public LabController(LabService labService) {
        this.labService = labService;
    }
    @PostMapping("/insertLab")
    private Result<String> insertLab(@RequestBody LabEntity labEntity){
        boolean b = labService.insertUser(labEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateLab")
    private Result<String> updateLab(@RequestBody LabEntity labEntity){
        boolean b = labService.updateUser(labEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @GetMapping("/queryLabTable")
    private Result<LabEntity> queryLabTable(@RequestBody LabEntity labEntity){
        labService.queryTable(labEntity);
        return Result.success("查询成功",labEntity);
    }

    @PostMapping("/deleteLab")
    private Result<LabEntity> deleteLabById(@RequestBody Integer id){
        boolean b = labService.deleteLabById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }

}
