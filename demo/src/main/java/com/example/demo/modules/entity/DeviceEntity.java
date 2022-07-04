package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.Date;


@Data

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity extends TableVO {
    @Alias("设备id")
    private Integer id;
    @Alias("设备名")
    private String devName;
    @Alias("设备价格")
    private String devPrice;
    @Alias("设备状态")
    private String devStatus;
    private DevLendEntity devLendEntity;



}
