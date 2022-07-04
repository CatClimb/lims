package com.example.demo.modules.dao;

import com.example.demo.common.Alias;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.vo.TableVO;
import lombok.*;
import org.springframework.stereotype.Repository;


public interface OutRecordDao extends Dao {
    public OutRecordEntity selectById(Integer id);
    public boolean updateByIdFixationOutStatus(OutRecordEntity outRecordEntity);
    public boolean deleteByIdFixationOutStatus(Integer id);
}
