package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;
import com.example.demo.modules.service.LabGdtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ad")
public class LabGdtController {
    private final LabGdtService labGdtService;

    @Autowired
    public LabGdtController(LabGdtService labGdtService){
        this.labGdtService=labGdtService;
    }
    @PostMapping("/insertLabGdt")
    public Result<String> insertLabGdt(@RequestBody LabGdtEntity labGdtEntity){
        log.info("labGdtEntity.toString():"+labGdtEntity.toString());
        boolean b = labGdtService.insert(labGdtEntity);
        if (b){
            return Result.success("添加成功");
        }else{
            return Result.fail("添加失败");
        }
    }
    @PostMapping("/updateLabGdt")
    public Result<String> updateLabGdt(@RequestBody LabGdtEntity labGdtEntity){
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
    @PostMapping("/deleteLabGdt/{id}")
    public Result<String> deleteLabGdtById(@PathVariable Integer id){
        boolean b = labGdtService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }


}
