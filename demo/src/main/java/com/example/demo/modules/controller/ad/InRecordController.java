package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.InRecordEntity;
import com.example.demo.modules.entity.InRecordEntity;
import com.example.demo.modules.service.InRecordService;
import com.example.demo.modules.service.InRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class InRecordController {
    public final InRecordService inRecordService;
    @Autowired
    public InRecordController(InRecordService inRecordService) {
        this.inRecordService = inRecordService;
    }
    @PostMapping("/insertInRecord")
    private Result<String> insertInRecord(@RequestBody InRecordEntity inRecordEntity){

        boolean b = inRecordService.insert(inRecordEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateInRecord")
    private Result<String> updateInRecord(@RequestBody InRecordEntity inRecordEntity){
        boolean b = inRecordService.update(inRecordEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryInRecordTable")
    private Result<InRecordEntity> queryInRecordTable(@RequestBody InRecordEntity inRecordEntity){
        log.info("inRecordEntity.toString():"+inRecordEntity.toString());
        inRecordService.setTable(inRecordEntity);
        return Result.success("查询成功",inRecordEntity);
    }

    @PostMapping("/deleteInRecord/{id}")
    private Result<InRecordEntity> deleteInRecordById(@PathVariable("id") Integer id){
        boolean b = inRecordService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
}

