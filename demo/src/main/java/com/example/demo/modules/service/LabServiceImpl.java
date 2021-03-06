package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.LabDao;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LabServiceImpl implements LabService {
    private final Logger LOG = LoggerFactory.getLogger(LabServiceImpl.class);
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
        tableVO.setTableHead(tableControlUtil.getTableHead(LabEntity.class,1));


    }
    @Override
    public void mulTableQueryLabGdt(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=labDao.mulTableQueryLabGdtCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(labDao.mulTableQueryLabGdt(tableVO));


//        log.info("xxxxxxxxxx"+comsumeDao.mulTablequeryInRecord(tableVO).toString());

        //????????????
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"labId","???????????????"});
        strings.add(new String[]{"labType","???????????????"});
        strings.add(new String[]{"lgTiming","????????????"});
        strings.add(new String[]{"lgStatus","????????????"});
        strings.add(new String[]{"lgDate","??????"});
        strings.add(new String[]{"userName","?????????"});
        tableVO.setTableHead(strings);
    }

    @Override
    public int mulTableQueryLabGdtCount(TableVO tableVO) {
        return labDao.mulTableQueryLabGdtCount(tableVO);
    }

    
}
