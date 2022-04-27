package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.InRecordEntity;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.modules.service.OutRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class OutRecordController {
    public final OutRecordService outRecordService;

    @Autowired
    public OutRecordController(OutRecordService outRecordService) {
        this.outRecordService = outRecordService;
    }

    @PostMapping("/insertOutRecord")
    private Result<String> insertOutRecord(@RequestBody OutRecordEntity outRecordEntity) {

        boolean b = outRecordService.insert(outRecordEntity);
        if (b) {
            return Result.success("添加成功");
        } else {
            return Result.fail("插入失败");
        }
    }


    @PostMapping("/updateOutRecord")
    private Result<String> updateOutRecord(@RequestBody OutRecordEntity outRecordEntity) {
        boolean b = outRecordService.update(outRecordEntity);
        if (b) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/deleteOutRecord/{id}")
    private Result<InRecordEntity> deleteOutRecordById(@PathVariable("id") Integer id){
        boolean b = outRecordService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
    @PostMapping("/queryOutRecordTable")
    private Result<OutRecordEntity> queryOutRecordTable(@RequestBody OutRecordEntity outRecordEntity) {
        log.info("outRecordEntity.toString():" + outRecordEntity.toString( ));
        outRecordService.setTable(outRecordEntity);
        return Result.success("查询成功", outRecordEntity);
    }


    @PostMapping("/outPass")
    private Result<OutRecordEntity> outPass(@RequestBody OutRecordEntity outRecordEntity) {
        boolean b = outRecordService.outPass(outRecordEntity);
        if (b) {
            return Result.success("出库成功");
        } else {
            return Result.fail("出库失败");
        }
    }    @PostMapping("/outNoPass")
    private Result<OutRecordEntity> outNoPass(@RequestBody OutRecordEntity outRecordEntity) {
        boolean b = outRecordService.outNoPass(outRecordEntity);
        if (b) {
            return Result.success("未批准出库成功");
        } else {
            return Result.fail("未批准出库失败");
        }
    }
}