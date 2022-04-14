package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.LabDao;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean insert(TableVO tableVO) {
        return labDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return labDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return labDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return labDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return labDao.conditionalQueryCount(tableVO);
    }
    @Override
    public void setTable(TableVO tableVO) {

        tableControlUtil.setTable(tableVO,labDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(LabDao.class));


    }


}
