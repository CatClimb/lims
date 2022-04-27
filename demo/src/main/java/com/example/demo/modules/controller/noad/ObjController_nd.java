package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.modules.service.ObjService;
import com.example.demo.vo.ObjSEVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/noad")
public class ObjController_nd {
    public final ObjService objService;
    @Autowired
    public ObjController_nd(ObjService objService) {
        this.objService = objService;
    }

    /**
     * 登记项目 u
     * @param objEntity
     * @return
     */

    @PostMapping("/registerObj")
    private Result<String> registerObj(@RequestBody ObjEntity objEntity){
        log.info("xxxxxxxxxxxxxx"+objEntity.toString());
        boolean b = objService.registerObj(objEntity);
        if (b){
            return Result.success("审批中");
        }
        else{
            return Result.fail("申请失败");
        }
    }

//    @PostMapping("/updateObj")
//    private Result<String> updateObj(@RequestBody ObjEntity objEntity){
//        boolean b = objService.update(objEntity);
//        if (b){
//            return Result.success("更新成功");
//        }
//        else{
//            return Result.fail("更新失败");
//        }
//    }

    @PostMapping("/queryObjTable")
    private Result<ObjEntity> queryObjTable(@RequestBody ObjEntity objEntity){
        log.info("objEntity.toString():"+objEntity.toString());
        objService.setTable(objEntity);
        return Result.success("查询成功",objEntity);
    }

    /**
     * 取消登录 d
     * @param id
     * @return
     */
    @PostMapping("/nRegisterObj/{id}")
    private Result<ObjEntity> nRegisterObj(@PathVariable("id") Integer id){
        boolean b = objService.nRegisterObj(id);
        if (b){
            return Result.success("已取消");
        }
        else{
            return Result.fail("取消失败");
        }
    }

//    @PostMapping("/recordObj")
//    private Result<ObjSEVO> RecordObjByRecordTimeBetween(@RequestBody ObjSEVO objSDVO){
//        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+objSDVO);
//        objService.RecordObjByRecordTimeBetween(objSDVO);
//        return Result.success("期间登记项目数量为"+objSDVO.getCount(),objSDVO);
//    }
}
