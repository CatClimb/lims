package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.InRecordDao;
import com.example.demo.modules.entity.InRecordEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InRecordServiceImpl implements InRecordService {

    private final InRecordDao inRecordDao;
    private final TableControlUtil<InRecordEntity> tableControlUtil;
    @Autowired
    public InRecordServiceImpl(TableControlUtil<InRecordEntity> tableControlUtil,InRecordDao inRecordDao){
        this.tableControlUtil=tableControlUtil;
        this.inRecordDao=inRecordDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return inRecordDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return inRecordDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return inRecordDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return inRecordDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return inRecordDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,inRecordDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(InRecordDao.class));
    }


}
