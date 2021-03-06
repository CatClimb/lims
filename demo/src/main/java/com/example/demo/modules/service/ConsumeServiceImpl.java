package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;

import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.dto.sme_outc.ApplyForOutStoreDTO;
import com.example.demo.modules.dao.ConsumeDao;
import com.example.demo.modules.dao.InRecordDao;
import com.example.demo.modules.dao.OutRecordDao;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.entity.InRecordEntity;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ConsumeServiceImpl implements ConsumeService {
    private final ConsumeDao consumeDao;
    private final OutRecordDao outRecordDao;
    private final InRecordDao inRecordDao;
    private final TableControlUtil<ConsumeEntity> tableControlUtil;
    @Autowired
    public ConsumeServiceImpl(TableControlUtil<ConsumeEntity> tableControlUtil, ConsumeDao consumeDao, OutRecordDao outRecordDao, InRecordDao inRecordDao){
        this.tableControlUtil=tableControlUtil;
        this.consumeDao=consumeDao;
        this.outRecordDao = outRecordDao;
        this.inRecordDao = inRecordDao;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean insert(TableVO tableVO) {
        ConsumeEntity consumeEntity=(ConsumeEntity) tableVO;
        boolean b =consumeDao.insert(tableVO);
        if (!b){
            return false;
        }
        InRecordEntity inRecordEntity = new InRecordEntity( );

        inRecordEntity.setInCount(consumeEntity.getSmeCount());
        inRecordEntity.setInTime(LocalDateTime.now());
        inRecordEntity.setUserName(TokenUtil.getUserNameByToken(ThreadTmp.getThreadLocalForToken()));
        ConsumeEntity consumeEntity1 = consumeDao.selectByConsumeName(consumeEntity.getSmeName( ));
        inRecordEntity.setSmeId(String.valueOf(consumeEntity1.getId()));
        log.info("xxxxxxxxxxxxxxxxxxxx"+inRecordEntity.toString());
        boolean insert = inRecordDao.insert(inRecordEntity);
        if (!insert){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;



    }

    @Override
    public boolean update(TableVO tableVO) {
        return consumeDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return consumeDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return consumeDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return consumeDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,consumeDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(ConsumeEntity.class,1));
    }

    @Override
    public void mulTableQueryInRecord(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=consumeDao.mulTableQueryInRecordCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(consumeDao.mulTableQueryInRecord(tableVO));


//        log.info("xxxxxxxxxx"+consumeDao.mulTableQueryInRecord(tableVO).toString());

        //????????????
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"inCount","????????????"});
        strings.add(new String[]{"userName","?????????"});
        strings.add(new String[]{"inTime","????????????"});
        strings.add(new String[]{"smeName","???????????????"});
        strings.add(new String[]{"smeCount","????????????"});
        strings.add(new String[]{"smeId","?????????id"});
        tableVO.setTableHead(strings);

        


    }

    @Override
    public void mulTableQueryOutRecord(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=consumeDao.mulTableQueryOutRecordCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(consumeDao.mulTableQueryOutRecord(tableVO));


//        log.info("xxxxxxxxxx"+consumeDao.mulTableQueryInRecord(tableVO).toString());

        //????????????
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"outCount","????????????"});
        strings.add(new String[]{"userName","?????????"});
        strings.add(new String[]{"outStatus","????????????"});
        strings.add(new String[]{"outReason","????????????"});
        strings.add(new String[]{"outTime","????????????"});
        strings.add(new String[]{"smeName","????????????"});
        strings.add(new String[]{"smeCount","??????"});
        strings.add(new String[]{"smeId","?????????id"});
        tableVO.setTableHead(strings);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean applyForOutStore(ApplyForOutStoreDTO applyForOutStoreDTO) {

        ConsumeEntity consumeEntity = new ConsumeEntity(applyForOutStoreDTO.getId(),applyForOutStoreDTO.getSmeName(),String.valueOf(applyForOutStoreDTO.getOutCount()),null,null);
        log.info("xxxxxxxxxxxxxxxx"+consumeDao);

        boolean update = consumeDao.updateByIdSub(consumeEntity);
        if(!update){
            return false;
        }
        OutRecordEntity outRecordEntity=new OutRecordEntity();
        outRecordEntity.setUserName(TokenUtil.getUserNameByToken(ThreadTmp.getThreadLocalForToken()));
        outRecordEntity.setOutCount(String.valueOf(applyForOutStoreDTO.getOutCount()));
        outRecordEntity.setOutReason(applyForOutStoreDTO.getOutReason());
        outRecordEntity.setOutStatus("?????????");
        outRecordEntity.setSmeId(String.valueOf(applyForOutStoreDTO.getId()));
//        outRecordEntity.setOutTime( LocalDateTime.now());
        boolean insert = outRecordDao.insert(outRecordEntity);
        if (!insert){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("yyyyyyyyyyyyyyyyyyy");
            return false;
        }else {
            return true;
        }




    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean cancelOutStore(OutRecordEntity outRecordEntity) {
        ConsumeEntity consumeEntity = consumeDao.selectById(Integer.valueOf(outRecordEntity.getSmeId( )));
        consumeEntity.setSmeCount(outRecordEntity.getOutCount());
        boolean update = consumeDao.updateByIdAdd(consumeEntity);
        if(!update){
            return false;
        }
        boolean update1 = outRecordDao.deleteByIdFixationOutStatus(outRecordEntity.getId());
        if(!update1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }else {
            return true;
        }


    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public boolean addCount(Integer id,Integer addCount) {
        ConsumeEntity consumeEntity = consumeDao.selectById(id);
        if(consumeEntity==null){
            return false;
        }
        consumeEntity.setSmeCount(String.valueOf(addCount));
        boolean b = consumeDao.updateByIdAdd(consumeEntity);
        if(!b){
            return false;
        }
        InRecordEntity inRecordEntity = new InRecordEntity( );
        inRecordEntity.setInCount(consumeEntity.getSmeCount());
        inRecordEntity.setInTime(LocalDateTime.now());
        inRecordEntity.setUserName(TokenUtil.getUserNameByToken(ThreadTmp.getThreadLocalForToken()));
        inRecordEntity.setSmeId(String.valueOf(id));
        boolean insert = inRecordDao.insert(inRecordEntity);
        if(!insert){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }else{
            return true;
        }

    }
}
