package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity extends TableVO {
    @Alias("设备id")
    private Integer id;
    @Alias("设备名")
    private String devName;
    @Alias("设备价格")
    private Float devPrice;
    @Alias("设备状态")
    private String devStatus;



}
