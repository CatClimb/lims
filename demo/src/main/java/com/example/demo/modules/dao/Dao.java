package com.example.demo.modules.dao;

import com.example.demo.modules.entity.UserEntity;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface Dao {
    public List<TableVO> queryTable(TableVO tableVO);
    public Integer queryTableCount(TableVO tableVO);
}
