package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.common.util.ThreadTmp;
import com.example.demo.common.util.TokenUtil;
import com.example.demo.modules.dao.DevLendDao;
import com.example.demo.modules.entity.DevLendEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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

    @Override
    public boolean handleDevLend(DevLendEntity devLendEntity) {
        devLendEntity.setUserName(TokenUtil.getUserNameByToken(ThreadTmp.getThreadLocalForToken()));
        devLendEntity.setDevUStatus("申请中");
        return devLendDao.updateFixationDevUStatus2(devLendEntity);
    }

    @Override
    //取消 也是提前归还 和逾期归还
    public boolean cancelDevLend(DevLendEntity devLendEntity) {
        devLendEntity.setUserName(null);
        devLendEntity.setBorrowTime(null);
        devLendEntity.setReturnTime(null);
        devLendEntity.setUserName(null);
        devLendEntity.setDevReason(null);
        devLendEntity.setDevUStatus("可借用");
        return devLendDao.updateFixationDevUStatus(devLendEntity);
    }

    @Override
    public boolean lendPass(DevLendEntity devLendEntity) {
        //检测设备状态吗
        devLendEntity.setDevUStatus("被借用");
        devLendEntity.setReturnStatus(false);
        log.info("xxxxxxxxxxxxxxxxxxxxxx"+devLendEntity.toString());

        return devLendDao.updateFixationDevUStatus(devLendEntity);
    }

    @Override
    public boolean lendNoPass(DevLendEntity devLendEntity) {
        //检测设备状态吗
        devLendEntity.setDevUStatus("可借用");
        devLendEntity.setUserName(null);
        devLendEntity.setBorrowTime(null);
        devLendEntity.setReturnTime(null);
        devLendEntity.setDevReason(null);//加通知吗

        log.info("xxxxxxxxxxxxxxxxxxxxxx"+devLendEntity.toString());
        return devLendDao.updateFixationDevUStatus(devLendEntity);

    }
}
