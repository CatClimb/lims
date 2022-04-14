package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.ObjDao;
import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjServiceImpl implements ObjService {
    private final ObjDao objDao;
    private final TableControlUtil<ObjEntity> tableControlUtil;
    @Autowired
    public ObjServiceImpl(ObjDao objDao,TableControlUtil<ObjEntity> tableControlUtil){
        this.objDao=objDao;
        this.tableControlUtil=tableControlUtil;
    }


    @Override
    public boolean insert(TableVO tableVO) {
        return objDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return objDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return objDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return objDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return objDao.conditionalQueryCount(tableVO);
    }
    @Override
    public void setTable(TableVO tableVO) {

        tableControlUtil.setTable(tableVO,objDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(ObjDao.class));


    }


}