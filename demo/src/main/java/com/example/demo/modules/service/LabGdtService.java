package com.example.demo.modules.service;

import com.example.demo.modules.entity.LabGdtEntity;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface LabGdtService{
    public boolean insert(TableVO tableVO);
    public boolean update(TableVO tableVO);
    public List<TableVO> conditionalQuery(TableVO tableVO);
    public Integer conditionalQueryCount(TableVO tableVO);
    public boolean deleteLabGdt(LabGdtEntity labGdtEntity);
    public void  setTable(TableVO tableVO);




}
