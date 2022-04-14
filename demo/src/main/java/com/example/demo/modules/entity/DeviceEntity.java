package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String devPrice;
    @Alias("借用状态")
    private String devUStatus;
    @Alias("设备状态")
    private String devStatus;
    @Alias("借用人")
    private String name;
    @Alias("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deviceSTime;
    @Alias("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deviceETime;
    @Alias("借用原因")
    private String devReason;
    @Alias("所属项目")
    private String objName;

}
