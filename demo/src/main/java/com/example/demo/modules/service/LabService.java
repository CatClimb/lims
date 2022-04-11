package com.example.demo.modules.service;

import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.UserEntity;

public interface LabService {
    public boolean insertUser(LabEntity labEntity);
    public boolean updateUser(LabEntity labEntity);
    public void queryTable(LabEntity labEntity);
    public boolean deleteLabById(Integer id);
}
