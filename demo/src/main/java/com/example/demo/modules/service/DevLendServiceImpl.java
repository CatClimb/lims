package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.DevLendDao;
import com.example.demo.modules.entity.DevLendEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DevLendServiceImpl implements DevLendService{
    private final DevLendDao devLendDao;
    private final TableControlUtil<DevLendEntity> tableControlUtil;
    @Autowired
    public DevLendServiceImpl(TableControlUtil<DevLendEntity> tableControlUtil,DevLendDao devLendDao){
        this.tableControlUtil=tableControlUtil;
        this.devLendDao=devLendDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return devLendDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return devLendDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return devLendDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return devLendDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return devLendDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,devLendDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(DevLendEntity.class,0));
    }
}
