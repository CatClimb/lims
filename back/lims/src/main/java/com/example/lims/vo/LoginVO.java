package com.example.lims.vo;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
//    @NotBlank(message = "用户名不能为空")
//    @Length(message = "请输入长度1~20的字符串",min=1,max=20)
    private String userName;
//    @NotBlank(message = "密码不能为空")
//    @Length(message = "请输入长度6~20的字符串",min=6,max=20)
    private String password;
}
