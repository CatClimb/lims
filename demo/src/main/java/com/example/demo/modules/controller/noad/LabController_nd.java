package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.dto.lab_labgdt.ConditionalForLLGDTO;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.service.LabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/noad")
public class LabController_nd {

    public final LabService labService;
    @Autowired
    public LabController_nd(LabService labService) {
        this.labService = labService;
    }
//    @PostMapping("/insertLab")
//    private Result<String> insertLab(@RequestBody LabEntity labEntity){
//
//        boolean b = labService.insert(labEntity);
//        if (b){
//            return Result.success("添加成功");
//        }
//        else{
//            return Result.fail("插入失败");
//        }
//    }

//    @PostMapping("/updateLab")
//    private Result<String> updateLab(@RequestBody LabEntity labEntity){
//        boolean b = labService.update(labEntity);
//        if (b){
//            return Result.success("更新成功");
//        }
//        else{
//            return Result.fail("更新失败");
//        }
//    }

//    @PostMapping("/queryLabTable")
//    private Result<LabEntity> queryLabTable(@RequestBody LabEntity labEntity){
//        log.info("labEntity.toString():"+labEntity.toString());
//        labService.setTable(labEntity);
//        return Result.success("查询成功",labEntity);
//    }
//
//    @PostMapping("/deleteLab/{id}")
//    private Result<LabEntity> deleteLabById(@PathVariable("id") Integer id){
//        boolean b = labService.deleteById(id);
//        if (b){
//            return Result.success("删除成功");
//        }
//        else{
//            return Result.fail("删除失败");
//        }
//    }

    /**
     * 多表查询 预约信息
     * @param conditionalForLLGDTO
     * @return
     */
    @PostMapping("/mulquerylg")
    private Result<ConditionalForLLGDTO> mulTableQueryLabGdt(@RequestBody ConditionalForLLGDTO conditionalForLLGDTO){
        log.info("conditionalForLLGDTO"+conditionalForLLGDTO);
        labService.mulTableQueryLabGdt(conditionalForLLGDTO);
        return Result.success("查询成功",conditionalForLLGDTO);
    }

}
