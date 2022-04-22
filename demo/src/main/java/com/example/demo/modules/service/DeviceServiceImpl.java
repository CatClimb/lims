package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.DeviceDao;
import com.example.demo.modules.dao.LabGdtDao;
import com.example.demo.modules.entity.DeviceEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService{
    private final DeviceDao deviceDao;
    private final TableControlUtil<DeviceEntity> tableControlUtil;
    @Autowired
    public DeviceServiceImpl(TableControlUtil<DeviceEntity> tableControlUtil,DeviceDao deviceDao){
        this.tableControlUtil=tableControlUtil;
        this.deviceDao=deviceDao;
    }
    @Override
    public boolean insert(TableVO tableVO) {
        return deviceDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return deviceDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deviceDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return deviceDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return deviceDao.conditionalQueryCount(tableVO);
    }

    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,deviceDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(DeviceEntity.class,0));
    }

    @Override
    public float devicePriceSum() {
        return deviceDao.devicePriceSum();
    }
}
