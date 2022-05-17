package com.example.demo.modules.controller.ad;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.RoleDao;
import com.example.demo.modules.entity.RoleEntity;
import com.example.demo.modules.service.RoleService;
import com.example.demo.vo.TableVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleControllerTest {
    @Autowired
    public  RoleService roleService;
    @Autowired
    public RoleDao roleDao;
    @Autowired
    public TableControlUtil tableControlUtil;
    @Test
    public void R(){

        RoleEntity roleEntity = new RoleEntity( );

        roleEntity.setPage(1);
        roleEntity.setPageSize(10);
        tableControlUtil.setTable(roleEntity,roleDao);
        roleEntity.setTableHead(tableControlUtil.getTableHead(RoleEntity.class,1));
        System.out.println(roleEntity );
        System.out.println(roleEntity.getTableHead() );
        System.out.println(roleEntity.getTableData() );
    }

}