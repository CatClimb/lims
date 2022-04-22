package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class LabGdtEntity extends TableVO {
    @Alias("id")
    private Integer id;
    @Alias("编号")
    private String labId;
    @Alias("预约时段")
    private String lgTiming;
    @Alias("预约状态")
    private String lgStatus;
    @Alias("日期")
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate lgDate;
    @Alias("使用者")
    private String name;
    @Alias("使用类别")
    private String lgType;

}
