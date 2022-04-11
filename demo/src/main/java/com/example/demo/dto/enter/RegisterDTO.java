package com.example.demo.dto.enter;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String userName;
    private String password;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String weChat;
}
