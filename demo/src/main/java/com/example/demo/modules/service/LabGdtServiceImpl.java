package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.LabGdtDao;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabGdtServiceImpl implements LabGdtService{

    private final LabGdtDao labGdtDao;
    private final TableControlUtil<LabGdtEntity> tableControlUtil;
    @Autowired
    public LabGdtServiceImpl(LabGdtDao labGdtDao,TableControlUtil<LabGdtEntity> tableControlUtil){
        this.labGdtDao=labGdtDao;
        this.tableControlUtil=tableControlUtil;
    }


    @Override
    public boolean insertLabGdt(LabGdtEntity labGdtEntity) {
        return labGdtDao.insertLabGdt(labGdtEntity);
    }

    @Override
    public boolean updateLabGdt(LabGdtEntity labGdtEntity) {
        return labGdtDao.updateLabGdt(labGdtEntity);
    }

    @Override
    public void queryTable(LabGdtEntity labGdtEntity) {
        tableControlUtil.setTable(labGdtEntity,labGdtDao);
        labGdtEntity.setTableHead(tableControlUtil.getTableHead(LabGdtEntity.class));
    }

    @Override
    public boolean deleteLabGdtById(Integer labId) {
        return false;
    }
}
