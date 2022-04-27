package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.DevLendEntity;
import com.example.demo.modules.service.DevLendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/noad")
public class DevLendController_nd {

    public final DevLendService devLendService;
    @Autowired
    public DevLendController_nd(DevLendService devLendService) {
        this.devLendService = devLendService;
    }
//    @PostMapping("/insertDevLend")
//    private Result<String> insertDevLend(@RequestBody DevLendEntity deviceEntity){
//
//        boolean b = devLendService.insert(deviceEntity);
//        if (b){
//            return Result.success("添加成功");
//        }
//        else{
//            return Result.fail("插入失败");
//        }
//    }

    @PostMapping("/handleDevLend")
    private Result<String> handleDevLend(@RequestBody DevLendEntity deviceEntity){
        boolean b = devLendService.handleDevLend(deviceEntity);
        if (b){
            return Result.success("申请中");
        }
        else{
            return Result.fail("借用失败");
        }
    }

//    @PostMapping("/queryDevLendTable")
//    private Result<DevLendEntity> queryDevLendTable(@RequestBody DevLendEntity deviceEntity){
//        log.info("deviceEntity.toString():"+deviceEntity.toString());
//        devLendService.setTable(deviceEntity);
//        return Result.success("查询成功",deviceEntity);
//    }

    @PostMapping("/cancelDevLend")
    private Result<String> cancelDevLend(@RequestBody DevLendEntity devLendEntity){
        boolean b = devLendService.cancelDevLend(devLendEntity);
        if (b){
            return Result.success("取消成功");
        }
        else{
            return Result.fail("取消失败");
        }
    }

}
