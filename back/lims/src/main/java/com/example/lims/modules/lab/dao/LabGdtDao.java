package com.example.lims.modules.lab.dao;

import com.example.lims.vo.LabGdtTableVO;
import com.example.lims.modules.lab.entity.LabGdtEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LabGdtDao {
 public List<LabGdtEntity> queryTable(LabGdtTableVO labGdtTableVO);
 public int queryCount(LabGdtTableVO labGdtTableVO);
}
