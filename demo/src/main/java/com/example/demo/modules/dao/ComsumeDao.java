package com.example.demo.modules.dao;

import com.example.demo.common.Alias;
import com.example.demo.dto.sme_inc.SmeAndIncordDTO;
import com.example.demo.modules.entity.ComsumeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComsumeDao extends Dao {
    public List<ComsumeEntity> a123();
}
