package com.example.demo.modules.service;


import com.example.demo.modules.entity.OutRecordEntity;

public interface OutRecordService extends Service {
    public boolean outPass(OutRecordEntity outRecordEntity);
    public boolean outNoPass(OutRecordEntity outRecordEntity);
}
