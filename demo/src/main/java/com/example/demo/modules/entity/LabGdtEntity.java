package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class LabGdtEntity extends TableVO {
    @Alias("编号")
    private String labId;
    @Alias("预约时段")
    private String lgTiming;
    @Alias("预约状态")
    private String lgStatus;
    @Alias("日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lgDate;
    @Alias("使用者")
    private String name;
    @Alias("使用类别")
    private String lgType;
    @Alias("所属项目名")
    private String objName;
}
