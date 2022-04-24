package com.example.demo.dto.sme_outc;

import com.example.demo.vo.TableVO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryConditionalSO extends TableVO {
private int outCount;
private String name;
private String outStatus;
private String outReason;
private int smeId;
private String smeName;
private int smeCount;
}
