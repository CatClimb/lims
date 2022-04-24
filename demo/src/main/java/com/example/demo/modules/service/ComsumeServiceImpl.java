package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.dto.sme_inc.QueryConditionalForSI;
import com.example.demo.modules.dao.ComsumeDao;
import com.example.demo.modules.entity.ComsumeEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
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

    @Override
    public void mulTablequeryInRecord(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=comsumeDao.mulTablequeryInRecordCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(comsumeDao.mulTablequeryInRecord(tableVO));


//        log.info("xxxxxxxxxx"+comsumeDao.mulTablequeryInRecord(tableVO).toString());

        //设置表头
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"inCount","入库数量"});
        strings.add(new String[]{"name","操作人"});
        strings.add(new String[]{"inTime","入库时间"});
        strings.add(new String[]{"smeName","易耗品名字"});
        strings.add(new String[]{"smeCount","库存数量"});
        strings.add(new String[]{"smeId","易耗品id"});
        tableVO.setTableHead(strings);

        


    }

    @Override
    public void mulTablequeryOutRecord(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=comsumeDao.mulTablequeryOutRecordCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(comsumeDao.mulTablequeryOutRecord(tableVO));


//        log.info("xxxxxxxxxx"+comsumeDao.mulTablequeryInRecord(tableVO).toString());

        //设置表头
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"outCount","出库数量"});
        strings.add(new String[]{"name","操作人"});
        strings.add(new String[]{"outStatus","出库状态"});
        strings.add(new String[]{"outReason","出库原因"});
        strings.add(new String[]{"outTime","出库时间"});
        strings.add(new String[]{"smeName","易耗品名"});
        strings.add(new String[]{"smeCount","库存"});
        strings.add(new String[]{"smeId","易耗品id"});
        tableVO.setTableHead(strings);
    }
}
