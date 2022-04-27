package com.example.demo.dto.sme_inc;

import com.example.demo.vo.TableVO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionalForSIDTO extends TableVO {

    private String inCount;
    private String userName;
    private String smeId;
    private String smeName;
    private String smeCount;

}
