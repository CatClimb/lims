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
public class InRecordEntity extends TableVO {
//    @Alias("id")
    private Integer id;
//    @Alias("入库数量")
    private String inCount;
//    @Alias("操作人")
    private String userName;
//    @Alias("入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private LocalDateTime inTime;
    private String smeId;






}
