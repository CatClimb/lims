package com.example.demo.dto.sme_outc;

import com.example.demo.vo.TableVO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionalSODTO extends TableVO {
private String outCount;
private String userName;
private String outStatus;
private String outReason;
private String smeId;
private String smeName;
private String smeCount;
}
