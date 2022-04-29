package com.example.demo.modules.dao;

import com.example.demo.modules.entity.LabGdtEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LabGdtDao extends Dao {

    public boolean deleteLabGdt(LabGdtEntity labGdtEntity);
    public boolean updateByIdFixationLgStatus(LabGdtEntity labGdtEntity);
}
