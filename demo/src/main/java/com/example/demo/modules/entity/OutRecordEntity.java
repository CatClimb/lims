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
public class OutRecordEntity extends TableVO {
//id
//sme_name
//out_count
//name
//out_status
//out_reason
//in_time
//obj_name
    @Alias("id")
    private Integer id;
    @Alias("易耗品名字")
    private String smeName;
    @Alias("出库数量")
    private String outCount;
    @Alias("操作人")
    private String name;
    @Alias("出库状态")
    private String outStatus;
    @Alias("出库原因")
    private String outReason;
    @Alias("出库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String outTime;
    @Alias("所属项目")
    private String objName;

}
