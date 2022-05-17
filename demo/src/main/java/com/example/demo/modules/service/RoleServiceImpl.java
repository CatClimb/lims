package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;

import com.example.demo.modules.dao.RoleDao;

import com.example.demo.modules.entity.RoleEntity;
import com.example.demo.vo.TableVO;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {


    private final RoleDao roleDao;
    private final TableControlUtil<RoleEntity> tableControlUtil;
    @Autowired
    public RoleServiceImpl(RoleDao roleDao, TableControlUtil<RoleEntity> tableControlUtil) {
        this.roleDao = roleDao;
        this.tableControlUtil = tableControlUtil;
    }


    @Override
    public boolean insert(TableVO tableVO) {
        return roleDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return roleDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return roleDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return roleDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return roleDao.conditionalQueryCount(tableVO);
    }
    @Override
    public void setTable(TableVO tableVO) {

        tableControlUtil.setTable(tableVO,roleDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(RoleEntity.class,1));
    }


}
