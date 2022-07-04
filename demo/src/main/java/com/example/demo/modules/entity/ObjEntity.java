package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ObjEntity extends TableVO {
    @Alias("项目id")
    private Integer id;
    @Alias("项目名")
    private String objName;
    @Alias("项目描述")
    private String objDescription;
    @Alias("项目状态")
    private String objStatus;
    @Alias("负责人")
    private String userName;
    @Alias("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private LocalDate objSTime;
    @Alias("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate objETime;
    @Alias("登记时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime recordTime;
    @Alias("申请理由")
    private String objReason;


}
