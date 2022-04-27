package com.example.demo.modules.controller.noad;

import com.example.demo.common.result.Result;
import com.example.demo.dto.dev_devl.ConditionalForDDLDTO;
import com.example.demo.modules.entity.DeviceEntity;
import com.example.demo.modules.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/noad")
public class DeviceController_nd {

    public final DeviceService deviceService;
    @Autowired
    public DeviceController_nd(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @PostMapping("/insertDevice")
    private Result<String> insertDevice(@RequestBody DeviceEntity deviceEntity){

        boolean b = deviceService.insert(deviceEntity);
        if (b){
            return Result.success("添加成功");
        }
        else{
            return Result.fail("插入失败");
        }
    }

    @PostMapping("/updateDevice")
    private Result<String> updateDevice(@RequestBody DeviceEntity deviceEntity){
        boolean b = deviceService.update(deviceEntity);
        if (b){
            return Result.success("更新成功");
        }
        else{
            return Result.fail("更新失败");
        }
    }

    @PostMapping("/queryDeviceTable")
    private Result<DeviceEntity> queryDeviceTable(@RequestBody DeviceEntity deviceEntity){
        log.info("deviceEntity.toString():"+deviceEntity.toString());
        deviceService.setTable(deviceEntity);
        return Result.success("查询成功",deviceEntity);
    }

    @PostMapping("/deleteDevice/{id}")
    private Result<DeviceEntity> deleteDeviceById(@PathVariable("id") Integer id){
        boolean b = deviceService.deleteById(id);
        if (b){
            return Result.success("删除成功");
        }
        else{
            return Result.fail("删除失败");
        }
    }
    @GetMapping("/devicePriceSum")
    private Result<Float> devicePriceSum(){
        return Result.success("统计成功",deviceService.devicePriceSum());
    }

    @PostMapping("/mulquerydl")
    public Result<ConditionalForDDLDTO> mulTableQueryDevLend(@RequestBody ConditionalForDDLDTO conditionalForDDLDTO){
        log.info("deviceEntity.toString():"+conditionalForDDLDTO.toString());
        deviceService.mulTableQueryDevLend(conditionalForDDLDTO);
        return Result.success("查询成功",conditionalForDDLDTO);
    }
}
