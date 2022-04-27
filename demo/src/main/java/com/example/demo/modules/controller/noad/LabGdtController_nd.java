package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.TableControlUtil;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;
import com.example.demo.modules.service.LabGdtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/noad")
public class LabGdtController_nd {
    private final LabGdtService labGdtService;

    @Autowired
    public LabGdtController_nd(LabGdtService labGdtService){
        this.labGdtService=labGdtService;
    }

    /**
     * 预约实验室 u
     * @param labGdtEntity
     * @return
     */
    @PostMapping("/orderLab")
    public Result<String> orderLab(@RequestBody LabGdtEntity labGdtEntity){

        boolean b = labGdtService.orderLab(labGdtEntity);
        if (b){
            return Result.success("预约成功");
        }else{
            return Result.fail("预约失败");
        }
    }

    /**
     * 取消预约 u
     * @param labGdtEntity
     * @return
     */
    @PostMapping("/nOrderLab")
    public Result<String> nOrderLab(@RequestBody LabGdtEntity labGdtEntity){

        boolean b = labGdtService.nOrderLab(labGdtEntity);
        if (b){
            return Result.success("取消成功");
        }else{
            return Result.fail("取消失败");
        }
    }

//    @PostMapping("/queryLabGdtTable")
//    public Result<LabGdtEntity> queryLabGdtTable(@RequestBody LabGdtEntity labGdtEntity){
//        labGdtService.setTable(labGdtEntity);
//        log.info("labGdtEntity.getLgDate();:"+labGdtEntity.getLgDate());
//        return Result.success("查询成功",labGdtEntity);
//    }


}
