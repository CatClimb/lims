package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.DevLendEntity;
import com.example.demo.modules.service.DevLendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ad")
public class DevLendController {

    public final DevLendService devLendService;
    @Autowired
    public DevLendController(DevLendService devLendService) {
        this.devLendService = devLendService;
    }
    @PostMapping("/insertDevLend")
    private Result<String> insertDevLend(@RequestBody DevLendEntity deviceEntity){

        boolean b = devLendService.insert(deviceEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateDevLend")
    private Result<String> updateDevLend(@RequestBody DevLendEntity deviceEntity){
        boolean b = devLendService.update(deviceEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryDevLendTable")
    private Result<DevLendEntity> queryDevLendTable(@RequestBody DevLendEntity deviceEntity){
        log.info("deviceEntity.toString():"+deviceEntity.toString());
        devLendService.setTable(deviceEntity);
        return Result.success("查询成功",deviceEntity);
    }

    @PostMapping("/deleteDevLend/{id}")
    private Result<DevLendEntity> deleteDevLendById(@PathVariable("id") Integer id){
        boolean b = devLendService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }

}
