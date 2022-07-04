package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class OutRecordEntity extends TableVO {
//    sme_id id   out_status out_reason out_time
//    @Alias("id")
    private Integer id;
//    @Alias("出库数量")
    private String outCount;
//    @Alias("操作人")
    private String userName;
//    @Alias("出库状态")
    private String outStatus;
//    @Alias("出库原因")
    private String outReason;
//    @Alias("出库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime outTime;
//    @Alias("易耗品id")
    private String smeId;


}
