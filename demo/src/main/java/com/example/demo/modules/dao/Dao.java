package com.example.demo.modules.dao;

import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 3.查看表
 */

public interface Dao {
    public boolean insert(TableVO tableVO);
    public boolean update(TableVO tableVO);
    public boolean deleteById(Integer id);
    public List<TableVO> conditionalQuery(TableVO tableVO);
    public Integer conditionalQueryCount(TableVO tableVO);


}
