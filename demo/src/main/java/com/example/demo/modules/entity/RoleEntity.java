package com.example.demo.modules.entity;

import com.example.demo.common.Alias;
import com.example.demo.vo.TableVO;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends TableVO {
    @Alias("角色id")
    private int id;
    @Alias("角色名")
    private String roleName;
    @Alias("角色描述")
    private String roleDescribe;
}
