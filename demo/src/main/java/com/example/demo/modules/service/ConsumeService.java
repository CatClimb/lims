package com.example.demo.modules.service;


import com.example.demo.dto.sme_outc.ApplyForOutStoreDTO;
import com.example.demo.modules.entity.OutRecordEntity;
import com.example.demo.vo.TableVO;

public interface ConsumeService extends Service {
    public void mulTableQueryInRecord(TableVO tableVO);
    public void mulTableQueryOutRecord(TableVO tableVO);
    public boolean applyForOutStore(ApplyForOutStoreDTO applyForOutStoreDTO);
    public boolean cancelOutStore(OutRecordEntity outRecordEntity);
}
