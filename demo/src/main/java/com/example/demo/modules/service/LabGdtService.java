package com.example.demo.modules.service;

import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;

public interface LabGdtService {
    public boolean insertLabGdt(LabGdtEntity labGdtEntity);
    public boolean updateLabGdt(LabGdtEntity labGdtEntity);
    public void queryTable(LabGdtEntity labGdtEntity);
    public boolean deleteLabGdtById(Integer labId);
}
