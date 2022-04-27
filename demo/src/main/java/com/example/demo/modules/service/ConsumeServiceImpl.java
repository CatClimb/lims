package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;

import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.dto.sme_outc.ApplyForOutStoreDTO;
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
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ConsumeServiceImpl implements ConsumeService {
    private final ConsumeDao consumeDao;
    private final OutRecordDao outRecordDao;
    private final TableControlUtil<ConsumeEntity> tableControlUtil;
    @Autowired
    public ConsumeServiceImpl(TableControlUtil<ConsumeEntity> tableControlUtil, ConsumeDao consumeDao, OutRecordDao outRecordDao){
        this.tableControlUtil=tableControlUtil;
        this.consumeDao=consumeDao;
        this.outRecordDao = outRecordDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return consumeDao.insert(tableVO);
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

        //设置表头
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"inCount","入库数量"});
        strings.add(new String[]{"userName","操作人"});
        strings.add(new String[]{"inTime","入库时间"});
        strings.add(new String[]{"smeName","易耗品名字"});
        strings.add(new String[]{"smeCount","库存数量"});
        strings.add(new String[]{"smeId","易耗品id"});
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

        //设置表头
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"outCount","出库数量"});
        strings.add(new String[]{"userName","操作人"});
        strings.add(new String[]{"outStatus","出库状态"});
        strings.add(new String[]{"outReason","出库原因"});
        strings.add(new String[]{"outTime","审批时间"});
        strings.add(new String[]{"smeName","易耗品名"});
        strings.add(new String[]{"smeCount","库存"});
        strings.add(new String[]{"smeId","易耗品id"});
        tableVO.setTableHead(strings);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean applyForOutStore(ApplyForOutStoreDTO applyForOutStoreDTO) {
        int calculateResult=applyForOutStoreDTO.getSmeCount()-applyForOutStoreDTO.getOutCount();
        ConsumeEntity consumeEntity = new ConsumeEntity(applyForOutStoreDTO.getId(),applyForOutStoreDTO.getSmeName(),String.valueOf(calculateResult),null,null);
        log.info("xxxxxxxxxxxxxxxx"+consumeDao);
        if(consumeDao.selectById(applyForOutStoreDTO.getId()).getSmeCount().equals(String.valueOf(applyForOutStoreDTO.getSmeCount()))){
            boolean update = consumeDao.update(consumeEntity);
            if(!update){
                return false;
            }
            OutRecordEntity outRecordEntity=new OutRecordEntity();
            outRecordEntity.setUserName(TokenUtil.getUserNameByToken(ThreadTmp.getThreadLocalForToken()));
            outRecordEntity.setOutCount(String.valueOf(applyForOutStoreDTO.getOutCount()));
            outRecordEntity.setOutReason(applyForOutStoreDTO.getOutReason());
            outRecordEntity.setOutStatus("出库中");
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
        }else{
            return false;
        }



    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public boolean cancelOutStore(OutRecordEntity outRecordEntity) {
        if(outRecordDao.selectById(outRecordEntity.getId()).getOutStatus().equals("出库中")){
            ConsumeEntity consumeEntity = consumeDao.selectById(Integer.valueOf(outRecordEntity.getSmeId( )));
            int calculateResult=Integer.parseInt(consumeEntity.getSmeCount())+Integer.parseInt(outRecordEntity.getOutCount());
            consumeEntity.setSmeCount(String.valueOf(calculateResult));
            boolean update = consumeDao.update(consumeEntity);
            if(!update){
                return false;
            }
            boolean update1 = outRecordDao.deleteById(outRecordEntity.getId());
            if(!update1){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }else {
                return true;
            }

        }else {
            TransactionAspectSupport.currentTransactionStatus( ).setRollbackOnly( );
            return false;
        }
    }
}
