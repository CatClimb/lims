package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.ConsumeDao;
import com.example.demo.modules.dao.OutRecordDao;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
public class OutRecordServiceImpl implements  OutRecordService {
    private final OutRecordDao outRecordDao;
    private final ConsumeDao consumeDao;
    private final TableControlUtil<OutRecordEntity> tableControlUtil;
    @Autowired
    public OutRecordServiceImpl(TableControlUtil<OutRecordEntity> tableControlUtil, OutRecordDao outRecordDao, ConsumeDao consumeDao){
        this.tableControlUtil=tableControlUtil;
        this.outRecordDao=outRecordDao;
        this.consumeDao = consumeDao;
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

    @Override
    public boolean outPass(OutRecordEntity outRecordEntity) {
        outRecordEntity.setOutStatus("已出库");
        outRecordEntity.setOutTime(LocalDateTime.now());
        return outRecordDao.updateByIdFixationOutStatus(outRecordEntity);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public boolean outNoPass(OutRecordEntity outRecordEntity) {
        ConsumeEntity consumeEntity = consumeDao.selectById(Integer.valueOf(outRecordEntity.getSmeId( )));
        int calculator = Integer.parseInt(consumeEntity.getSmeCount()) + Integer.parseInt(outRecordEntity.getOutCount( ));
        consumeEntity.setSmeCount(String.valueOf(calculator));
        boolean update = consumeDao.update(consumeEntity);
        if(!update){
            log.info("consumeEntity update出问题");
            return false;
        }
        outRecordEntity.setOutStatus("未出库");
        outRecordEntity.setOutTime(LocalDateTime.now());
        boolean update1 = outRecordDao.updateByIdFixationOutStatus(outRecordEntity);
        if (!update1) {
            log.info("outRecordEntity update出问题");
            TransactionAspectSupport.currentTransactionStatus( ).setRollbackOnly( );
            return false;
        }else {
            return true;
        }




    }
}
