package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.DeviceDao;
import com.example.demo.modules.dao.LabGdtDao;
import com.example.demo.modules.entity.DeviceEntity;
import com.example.demo.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void mulTableQueryDevLend(TableVO tableVO) {
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();

        int count=deviceDao.mulTableQueryDevLendCount(tableVO);
        if(start>count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);

        tableVO.setTableData(deviceDao.mulTableQueryDevLend(tableVO));

//        log.info("xxxxxxxxxx"+consumeDao.mulTableQueryInRecord(tableVO).toString());
        //????????????
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"devName","?????????"});
        strings.add(new String[]{"devPrice","????????????"});
        strings.add(new String[]{"devStatus","????????????"});
        strings.add(new String[]{"devUStatus","????????????"});
        strings.add(new String[]{"userName","?????????"});
        strings.add(new String[]{"borrowTime","????????????"});
        strings.add(new String[]{"returnTime","????????????"});
        strings.add(new String[]{"devReason","????????????"});
        strings.add(new String[]{"returnStatus","????????????"});

        tableVO.setTableHead(strings);
    }
}
