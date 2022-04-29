package com.example.demo.modules.service;

import com.example.demo.modules.entity.DevLendEntity;

public interface DevLendService extends Service {
    public boolean handleDevLend(DevLendEntity devLendEntity);
    public boolean cancelDevLend(DevLendEntity devLendEntity);
    public boolean lendPass(DevLendEntity devLendEntity);
    public boolean lendNoPass(DevLendEntity devLendEntity);
}
