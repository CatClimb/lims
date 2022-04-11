package com.example.demo.dto.enter;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private String userName;
    private String password;
}
