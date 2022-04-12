package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.LabDao;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class LabServiceImpl implements LabService {
    private final LabDao labDao;
    private final TableControlUtil<LabEntity> tableControlUtil;
    @Autowired
    public LabServiceImpl(LabDao labDao, TableControlUtil<LabEntity> tableControlUtil) {
        this.labDao = labDao;
        this.tableControlUtil = tableControlUtil;
    }

    @Override
    public boolean insertUser(LabEntity labEntity) {
        return labDao.insertLab(labEntity);
    }

    @Override
    public boolean updateUser(LabEntity labEntity) {
        return labDao.updateLab(labEntity);
    }

    @Override
    public void queryTable(LabEntity labEntity) {
        tableControlUtil.setTable(labEntity,labDao);
        labEntity.setTableHead(tableControlUtil.getTableHead(LabEntity.class));
    }

    @Override
    public boolean deleteLabById(Integer id) {
        return labDao.deleteLabById(id);
    }
}
