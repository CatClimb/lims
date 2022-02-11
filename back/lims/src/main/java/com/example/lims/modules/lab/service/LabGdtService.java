package com.example.lims.modules.lab.service;

import com.example.lims.vo.LabGdtTableVO;
import com.example.lims.modules.lab.entity.LabGdtEntity;

import java.util.List;

public interface LabGdtService {
    public List<LabGdtEntity> queryTable(LabGdtTableVO labGdtTableVO);
    public int queryCount(LabGdtTableVO labGdtTableVO);
}
