package com.example.lims.modules.lab.service;

import com.example.lims.modules.lab.dao.LabGdtDao;
import com.example.lims.vo.LabGdtTableVO;
import com.example.lims.modules.lab.entity.LabGdtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LabGdtServiceImpl implements LabGdtService {
    public LabGdtDao labGdtDao;
    @Autowired
    public LabGdtServiceImpl(LabGdtDao labGdtDao){
        this.labGdtDao=labGdtDao;
    }
    public List<LabGdtEntity> queryTable(LabGdtTableVO labGdtTableVO){
        return labGdtDao.queryTable(labGdtTableVO);
    }
    public int queryCount(LabGdtTableVO labGdtTableVO){
        return labGdtDao.queryCount(labGdtTableVO);
    }
}
