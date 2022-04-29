package com.example.demo.modules.controller.ad;

import com.example.demo.common.result.Result;
import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.modules.service.ObjService;
import com.example.demo.vo.ObjSEVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ad")
public class ObjController {
    public final ObjService objService;
    @Autowired
    public ObjController(ObjService objService) {
        this.objService = objService;
    }
    @PostMapping("/insertObj")
    private Result<String> insertObj(@RequestBody ObjEntity objEntity){

        boolean b = objService.insert(objEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateObj")
    private Result<String> updateObj(@RequestBody ObjEntity objEntity){
        boolean b = objService.update(objEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryObjTable")
    private Result<ObjEntity> queryObjTable(@RequestBody ObjEntity objEntity){
        log.info("objEntity.toString():"+objEntity.toString());
        objService.setTable(objEntity);
        return Result.success("查询成功",objEntity);
    }

    @PostMapping("/deleteObj/{id}")
    private Result<ObjEntity> deleteObjById(@PathVariable("id") Integer id){
        boolean b = objService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/recordObj")
    private Result<ObjSEVO> RecordObjByRecordTimeBetween(@RequestBody ObjSEVO objSDVO){
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+objSDVO);
        objService.RecordObjByRecordTimeBetween(objSDVO);
        return Result.success("期间登记项目数量为"+objSDVO.getCount(),objSDVO);
    }

    @PostMapping("/objPass")
    private Result<ObjSEVO> objPass(@RequestBody ObjEntity objEntity){
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        boolean b = objService.objPass(objEntity);
        if (b){
            return Result.success("操作成功");
        }else{
            return Result.fail("操作失败");
        }


    }
    @PostMapping("/objNoPass")
    private Result<ObjSEVO> objNoPass(@RequestBody ObjEntity objEntity){
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        boolean b = objService.objNoPass(objEntity);
        if (b){
            return Result.success("操作成功");
        }else{
            return Result.fail("操作失败");
        }

    }
}
