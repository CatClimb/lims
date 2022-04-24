package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DevLendEntity extends TableVO {

    @Alias("设备id")
    private Integer id;
    @Alias("借用状态")
    private String devUStatus;
    @Alias("借用人")
    private String userName;
    @Alias("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime deviceSTime;
    @Alias("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime deviceETime;
    @Alias("借用原因")
    private String devReason;
}
