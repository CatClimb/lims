package com.example.demo.modules.dao;

import com.example.demo.modules.entity.LabEntity;
import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LabDao extends Dao {
    public List<LabEntity> mulTableQueryLabGdt(TableVO tableVO);
    public int mulTableQueryLabGdtCount(TableVO tableVO);
}
