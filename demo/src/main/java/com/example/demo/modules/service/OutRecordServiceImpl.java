package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.OutRecordDao;
import com.example.demo.modules.dao.OutRecordDao;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutRecordServiceImpl implements  OutRecordService {
    private final OutRecordDao outRecordDao;
    private final TableControlUtil<OutRecordEntity> tableControlUtil;
    @Autowired
    public OutRecordServiceImpl(TableControlUtil<OutRecordEntity> tableControlUtil,OutRecordDao outRecordDao){
        this.tableControlUtil=tableControlUtil;
        this.outRecordDao=outRecordDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return outRecordDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return outRecordDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return outRecordDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return outRecordDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return outRecordDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,outRecordDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(OutRecordEntity.class,1));
    }

}
