package com.example.demo.modules.dao;

import com.example.demo.vo.TableVO;

import java.util.List;
/**
 * 3.查看表
 */

public interface TableDao {
    public List<TableVO> queryTable(TableVO tableVO);
    public Integer queryTableCount(TableVO tableVO);

}
