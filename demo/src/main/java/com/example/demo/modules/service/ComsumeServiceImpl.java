package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.ComsumeDao;
import com.example.demo.modules.entity.ComsumeEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComsumeServiceImpl implements ComsumeService{
    private final ComsumeDao comsumeDao;
    private final TableControlUtil<ComsumeEntity> tableControlUtil;
    @Autowired
    public ComsumeServiceImpl(TableControlUtil<ComsumeEntity> tableControlUtil,ComsumeDao comsumeDao){
        this.tableControlUtil=tableControlUtil;
        this.comsumeDao=comsumeDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return comsumeDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return comsumeDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return comsumeDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return comsumeDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return comsumeDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,comsumeDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(ComsumeEntity.class,1));
    }
}
