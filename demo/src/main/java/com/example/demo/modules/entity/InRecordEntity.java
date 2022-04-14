package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class InRecordEntity extends TableVO {
//id
//sme_name
//in_count
//name
//in_time
    @Alias("id")
    private Integer id;
    @Alias("易耗品名字")
    private String smeName;
    @Alias("入库数量")
    private String inCount;
    @Alias("操作人")
    private String name;
    @Alias("入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String inTime;



}
