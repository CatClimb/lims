package com.example.demo.modules.dao;

import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.LabGdtEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LabGdtDao extends TableDao {
    public boolean insertLabGdt(LabGdtEntity labGdtEntity);
    public boolean updateLabGdt(LabGdtEntity labGdtEntity);
    public boolean deleteLabGdtById(Integer labId);
}
