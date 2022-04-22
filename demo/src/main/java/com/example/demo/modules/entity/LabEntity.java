package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LabEntity extends TableVO {
//    @Alias("实验室id")
    private Integer id;
    @Alias("实验室编号")
    private String labId;
    @Alias("实验室类别")
    private String labType;
}
