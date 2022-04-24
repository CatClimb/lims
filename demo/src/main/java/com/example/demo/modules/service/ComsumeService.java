package com.example.demo.modules.service;


import com.example.demo.dto.sme_inc.QueryConditionalForSI;
import com.example.demo.modules.entity.ComsumeEntity;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface ComsumeService extends Service {
    public void mulTablequeryInRecord(TableVO tableVO);
    public void mulTablequeryOutRecord(TableVO tableVO);
}
