package com.example.demo.vo;

import com.example.demo.modules.entity.UserEntity;
import lombok.*;

import java.util.List;
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TableVO {
    private Integer page;
    private Integer pageSize;
    private Integer start;
    private Integer count;
    private List<? extends TableVO> tableData;
    //用于存储相关数据来发送到前端     这就是不用hashMap的坏处
    private List<String[]> tableHead;
}
