package com.example.demo.modules.service;

import com.example.demo.modules.entity.LabGdtEntity;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface LabGdtService extends Service{
    public boolean nOrderLab(LabGdtEntity labGdtEntity);
    public boolean orderLab(LabGdtEntity labGdtEntity);




}
