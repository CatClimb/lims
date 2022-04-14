package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.AdviceEntity;
import com.example.demo.modules.entity.AdviceEntity;
import com.example.demo.modules.service.AdviceService;
import com.example.demo.modules.service.AdviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class AdviceController {
    public final AdviceService adviceService;
    @Autowired
    public AdviceController(AdviceService adviceService) {
        this.adviceService = adviceService;
    }
    @PostMapping("/insertAdvice")
    private Result<String> insertAdvice(@RequestBody AdviceEntity adviceEntity){

        boolean b = adviceService.insert(adviceEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateAdvice")
    private Result<String> updateAdvice(@RequestBody AdviceEntity adviceEntity){
        boolean b = adviceService.update(adviceEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryAdviceTable")
    private Result<AdviceEntity> queryAdviceTable(@RequestBody AdviceEntity adviceEntity){
        log.info("adviceEntity.toString():"+adviceEntity.toString());
        adviceService.setTable(adviceEntity);
        return Result.success("查询成功",adviceEntity);
    }

    @PostMapping("/deleteAdvice/{id}")
    private Result<AdviceEntity> deleteAdviceById(@PathVariable("id") Integer id){
        boolean b = adviceService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
}
