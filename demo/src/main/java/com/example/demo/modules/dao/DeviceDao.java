package com.example.demo.modules.dao;

import com.example.demo.modules.entity.DevLendEntity;
import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DeviceDao extends Dao {
    public float devicePriceSum();
    public List<DevLendEntity> mulTableQueryDevLend(TableVO tableVO);
    public int mulTableQueryDevLendCount(TableVO tableVO);

}
