package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.dto.sme_inc.QueryConditionalForSI;
import com.example.demo.modules.entity.ComsumeEntity;
import com.example.demo.modules.service.ComsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class ComsumeController {
    public final ComsumeService comsumeService;

    @Autowired
    public ComsumeController(ComsumeService comsumeService) {
        this.comsumeService = comsumeService;
    }
    @PostMapping("/insertComsume")
    private Result<String> insertComsume(@RequestBody ComsumeEntity comsumeEntity){

        boolean b = comsumeService.insert(comsumeEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateComsume")
    private Result<String> updateComsume(@RequestBody ComsumeEntity comsumeEntity){
        boolean b = comsumeService.update(comsumeEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryComsumeTable")
    private Result<ComsumeEntity> queryComsumeTable(@RequestBody ComsumeEntity comsumeEntity){
        log.info("comsumeEntity.toString():"+comsumeEntity.toString());
        comsumeService.setTable(comsumeEntity);
        return Result.success("查询成功",comsumeEntity);
    }

    @PostMapping("/deleteComsume/{id}")
    private Result<ComsumeEntity> deleteComsumeById(@PathVariable("id") Integer id){
        boolean b = comsumeService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
    @PostMapping("/mtqueryi")
    private Result mulTablequeryInRecord(@RequestBody QueryConditionalForSI queryConditionalForSI){

        return Result.success("",comsumeEntity);
    }
}

