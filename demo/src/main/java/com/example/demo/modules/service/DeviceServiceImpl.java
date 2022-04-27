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
        //设置表头
        List<String[]> strings = new ArrayList<>( );
        strings.add(new String[]{"devName","设备名"});
        strings.add(new String[]{"devPrice","设备价格"});
        strings.add(new String[]{"devStatus","设备状态"});
        strings.add(new String[]{"devUStatus","借用状态"});
        strings.add(new String[]{"userName","借用人"});
        strings.add(new String[]{"deviceSTime","开始时间"});
        strings.add(new String[]{"deviceETime","结束时间"});
        strings.add(new String[]{"devReason","借用原因"});

        tableVO.setTableHead(strings);
    }
}
