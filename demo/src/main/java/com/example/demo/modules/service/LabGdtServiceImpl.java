package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.LabGdtDao;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabGdtServiceImpl implements LabGdtService {

    private final LabGdtDao labGdtDao;
    private final TableControlUtil<LabGdtEntity> tableControlUtil;

    @Autowired
    public LabGdtServiceImpl(LabGdtDao labGdtDao, TableControlUtil<LabGdtEntity> tableControlUtil) {
        this.labGdtDao = labGdtDao;
        this.tableControlUtil = tableControlUtil;
    }


    @Override
    public boolean insert(TableVO tableVO) {
        return labGdtDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return labGdtDao.update(tableVO);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return labGdtDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return labGdtDao.conditionalQueryCount(tableVO);
    }
    @Override
    public boolean deleteLabGdt(LabGdtEntity labGdtEntity) {
        return labGdtDao.deleteLabGdt(labGdtEntity );
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,labGdtDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(LabGdtDao.class));


    }
}


