package com.example.demo.modules.service;


import com.example.demo.dto.sme_inc.QueryConditionalForSI;
import com.example.demo.modules.entity.ComsumeEntity;

import java.util.List;

public interface ComsumeService extends Service {
    public void mulTablequeryInRecord(QueryConditionalForSI queryConditionalForSI);
}
