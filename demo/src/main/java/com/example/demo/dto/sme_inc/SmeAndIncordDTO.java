package com.example.demo.dto.sme_inc;

import com.example.demo.common.Alias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@ToString
public class SmeAndIncordDTO {
    //in_record的id
    @Alias("id")
    private Integer id;
    @Alias("易耗品名字")
    private String smeName;
    @Alias("入库数量")
    private String inCount;
    @Alias("操作人")
    private String name;
    @Alias("入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime inTime;

}
