package com.example.demo.dto.sme_outc;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplyForOutStoreDTO {
    //该id为consume  id
private int id;
private String smeName;
private int smeCount;
private int outCount;
private String outReason;
}
