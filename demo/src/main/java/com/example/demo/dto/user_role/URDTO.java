package com.example.demo.dto.user_role;

import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class URDTO {
    private String userId;
    private String roleId;

}
