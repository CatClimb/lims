package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;
import java.util.List;
import java.awt.*;
import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity extends TableVO {
//    @Alias("用户id")
    private Integer id;
    @Alias("用户名")
    private String userName;
    @Alias("密码")
    private String password;
    @Alias("姓名")
    private String name;
    @Alias("性别")
    private String sex;
    @Alias("手机号")
    private String phone;
    @Alias("邮箱")
    private String email;
    @Alias("微信")
    private String weChat;

    @Alias("权限")
    private String auth;

    private List<RoleEntity> roleEntities;
//    private LocalDateTime loginTime;
}
