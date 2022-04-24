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

    @PostMapping("/updateLabGdt")
    public Result<String> updateLabGdt(@RequestBody LabGdtEntity labGdtEntity){
        String token = ThreadTmp.getThreadLocalForToken( );
       labGdtEntity.setUserName(TokenUtil.getUserNameByToken(token));
        boolean b = labGdtService.update(labGdtEntity);
        if (b){
            return Result.success("更新成功");
        }else{
            return Result.fail("更新失败");
        }
    }
    @PostMapping("/queryLabGdtTable")
    public Result<LabGdtEntity> queryLabGdtTable(@RequestBody LabGdtEntity labGdtEntity){
        labGdtService.setTable(labGdtEntity);
        log.info("labGdtEntity.getLgDate();:"+labGdtEntity.getLgDate());
        return Result.success("查询成功",labGdtEntity);
    }


}
