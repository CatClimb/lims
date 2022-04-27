package com.example.demo.dto.lab_labgdt;

import com.example.demo.vo.TableVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConditionalForLLGDTO extends TableVO {
    private String labId;
    private String labType;
    private String lgTiming;
    private String lgStatus;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    private LocalDate lgDate;
}
