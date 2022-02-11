package com.example.lims.vo;

import com.example.lims.modules.lab.entity.LabGdtEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LabGdtTableVO {
    private Integer page;
    private Integer pageSize;
    //用存相关数据来发送到前端     这就是不用hashMap的坏处
    private Integer start;
    private Integer count;
    private Integer labId;
    private String lgTiming;
    private String lgStatus;
    private Date lgSTime;
    private Date lgETime;
    private String name;
    private String lgType;
    private String objName;
    //用存相关数据来发送到前端     这就是不用hashMap的坏处 好与坏你看着办吧
    private List<LabGdtEntity> tableData;
    //用存相关数据来发送到前端     这就是不用hashMap的坏处
    private List<String[]> tableHead;
}
