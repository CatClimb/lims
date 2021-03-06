package com.example.demo.modules.dao;

import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ConsumeDao extends Dao {
    public List<ConsumeEntity> mulTableQueryInRecord(TableVO tableVO);
    public List<ConsumeEntity> mulTableQueryOutRecord(TableVO tableVO);
    public Integer mulTableQueryInRecordCount(TableVO tableVO);
    public Integer mulTableQueryOutRecordCount(TableVO tableVO);
    public ConsumeEntity selectById(Integer id);
    public ConsumeEntity selectByConsumeName(String smeName);
    public boolean updateByIdAdd(ConsumeEntity consumeEntity);
    public boolean updateByIdSub(ConsumeEntity consumeEntity);

}
