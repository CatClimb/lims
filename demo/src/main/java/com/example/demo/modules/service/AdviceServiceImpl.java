package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.AdviceDao;
import com.example.demo.modules.entity.AdviceEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {
    private final AdviceDao adviceDao;
    private final TableControlUtil<AdviceEntity> tableControlUtil;
    @Autowired
    public AdviceServiceImpl(TableControlUtil<AdviceEntity> tableControlUtil,AdviceDao adviceDao){
        this.tableControlUtil=tableControlUtil;
        this.adviceDao=adviceDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return adviceDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return adviceDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return adviceDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return adviceDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return adviceDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,adviceDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(AdviceDao.class));
    }
}
