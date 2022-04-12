package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class LabGdtEntity extends TableVO {
    @Alias("编号")
    private Integer labId;
    @Alias("预约时段")
    private String lgTiming;
    @Alias("预约状态")
    private String lgStatus;
    @Alias("开始时间")
    private Date lgSTime;
    @Alias("结束时间")
    private Date lgETime;
    @Alias("使用者")
    private String name;
    @Alias("使用类别")
    private String lgType;
    @Alias("所属项目名")
    private String objName;
}
