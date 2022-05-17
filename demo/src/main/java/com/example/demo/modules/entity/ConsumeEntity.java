package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
public class ConsumeEntity extends TableVO implements Serializable {

    @Alias("id")
    private Integer id;
    @Alias("易耗品名字")
    private String smeName;
    @Alias("易耗品数量")
    private String smeCount;
    private List<InRecordEntity> inRecordEntities;
    private List<OutRecordEntity> outRecordEntities;


}
