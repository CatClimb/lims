package com.example.demo.modules.dao;

import com.example.demo.modules.entity.DevLendEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DevLendDao extends Dao{
    public boolean updateFixationDevUStatus(DevLendEntity devLendEntity);
    public boolean updateFixationDevUStatus2(DevLendEntity devLendEntity);
}
