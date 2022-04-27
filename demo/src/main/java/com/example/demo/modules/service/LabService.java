package com.example.demo.modules.service;

import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.vo.TableVO;

public interface LabService extends Service{
    public void mulTableQueryLabGdt(TableVO tableVO);
    public int mulTableQueryLabGdtCount(TableVO tableVO);
}
