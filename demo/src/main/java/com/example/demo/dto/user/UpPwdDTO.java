package com.example.demo.dto.user;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpPwdDTO {

private String passwordO;
private String passwordN;
}
