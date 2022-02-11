package com.example.lims.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class  UpdatePwdDTO {
 private Integer id;
 private String password;
 private String passwordN;
 private String againPassword;
}
