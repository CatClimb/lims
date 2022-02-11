package com.example.lims.modules.lab.entity;

import com.example.lims.common.annotation.Alias;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LabGdtEntity {
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
