package com.example.demo.modules.controller.noad;


import com.example.demo.common.result.Result;
import com.example.demo.dto.sme_inc.ConditionalForSIDTO;
import com.example.demo.dto.sme_outc.ApplyForOutStoreDTO;
import com.example.demo.dto.sme_outc.ConditionalSODTO;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.modules.service.ConsumeService;
import com.example.demo.modules.service.ConsumeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController


@RequestMapping(value = "/noad")
public class ConsumeController_nd {
    public final ConsumeService consumeService;
    @Autowired
    public ConsumeController_nd(ConsumeService consumeService) {
        this.consumeService = consumeService;
    }
    @PostMapping("/queryConsumeTable")
    private Result<ConsumeEntity> queryConsumeTable(@RequestBody ConsumeEntity ConsumeEntity){
        log.info("ConsumeEntity.toString():"+ConsumeEntity.toString());
        consumeService.setTable(ConsumeEntity);
        return Result.success("查询成功",ConsumeEntity);
    }
    @PostMapping("/mtqueryo")
    private Result<ConditionalSODTO> mulTableQueryOutRecord(@RequestBody ConditionalSODTO conditionalSODTO){
        consumeService.mulTableQueryOutRecord(conditionalSODTO);
        return Result.success("查询成功",conditionalSODTO);
    }


    @PostMapping("/applyForOutStore")
    public Result<String> applyForOutStore(@RequestBody ApplyForOutStoreDTO applyForOutStoreDTO) {
        boolean b = consumeService.applyForOutStore(applyForOutStoreDTO);
        if (b) {
            return Result.success("申请中");
        } else {
            return Result.fail("申请失败");
        }
    }

    @PostMapping("/cancelOutStore")
    public Result<String> cancelOutStore(@RequestBody OutRecordEntity outRecordEntity) {
        boolean b = consumeService.cancelOutStore(outRecordEntity);
        if (b) {
            return Result.success("取消成功");
        } else {
            return Result.fail("取消失败");
        }
    }
}

