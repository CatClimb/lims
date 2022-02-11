package com.example.lims.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
//普通用户注册VO

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private Integer id;
//    @NotBlank(message = "用户名不能为空")
//    @Length(message = "请输入长度1~20的字符串",min=1,max=20)
    private String userName;

//    @NotBlank(message = "密码不能为空")
//    @Length(message = "请输入长度6~200的字符串",min=6,max=200)
    private String password;

//    @NotBlank(message = "姓名不能为空")
//    @Length(message = "请输入长度2~20的字符串",min=2,max=20)
    private String name;

    private String sex;

//    @NotBlank(message = "手机号不能为空")
//    @Length(message = "请输入长度11~20的字符串",min=11,max=20)
    private String phone;

//    @NotBlank(message = "邮箱不能为空")
//    @Email(message = "邮箱格式不正确")
    private String email;

//    @NotBlank(message = "微信不能为空")
//    @Length(message = "请输入长度6~20的字符串",min=6,max=20)
    private String weChat;

}
