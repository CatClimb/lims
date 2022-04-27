package com.example.demo.dto.dev_devl;

import com.example.demo.vo.TableVO;
import lombok.*;
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionalForDDLDTO extends TableVO {
private String devUStatus;
private String userName;
private String devReason;
private String devName;
private String devPrice;
private String devStatus;
}
