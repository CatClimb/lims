package com.example.demo.modules.controller.ad;


import com.example.demo.common.result.Result;
import com.example.demo.dto.sme_inc.ConditionalForSIDTO;
import com.example.demo.dto.sme_outc.ApplyForOutStoreDTO;
import com.example.demo.dto.sme_outc.ConditionalSODTO;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.service.ConsumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/ad")
public class ConsumeController {
    public final ConsumeService consumeService;

    @Autowired
    public ConsumeController(ConsumeService consumeService) {
        this.consumeService = consumeService;
    }
    @PostMapping("/insertConsume")
    private Result<String> insertConsume(@RequestBody ConsumeEntity ConsumeEntity){

        boolean b = consumeService.insert(ConsumeEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateConsume")
    private Result<String> updateConsume(@RequestBody ConsumeEntity ConsumeEntity){
        boolean b = consumeService.update(ConsumeEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryConsumeTable")
    private Result<ConsumeEntity> queryConsumeTable(@RequestBody ConsumeEntity ConsumeEntity){
        log.info("ConsumeEntity.toString():"+ConsumeEntity.toString());
        consumeService.setTable(ConsumeEntity);
        return Result.success("查询成功",ConsumeEntity);
    }

    @PostMapping("/deleteConsume/{id}")
    private Result<ConsumeEntity> deleteConsumeById(@PathVariable("id") Integer id){
        boolean b = consumeService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
    @PostMapping("/mtqueryi")
    private Result<ConditionalForSIDTO> mulTableQueryInRecord(@RequestBody ConditionalForSIDTO conditionalForSIDTO){
        consumeService.mulTableQueryInRecord(conditionalForSIDTO);
        return Result.success("查询成功",conditionalForSIDTO);
    }
    @PostMapping("/mtqueryo")
    private Result<ConditionalSODTO> mulTableQueryOutRecord(@RequestBody ConditionalSODTO conditionalSODTO){
        consumeService.mulTableQueryOutRecord(conditionalSODTO);
        return Result.success("查询成功",conditionalSODTO);
    }


}

