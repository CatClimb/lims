package com.example.demo.modules.dao;

import com.example.demo.modules.entity.LabEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LabDao extends Dao {
    /**
     * 1.添加实验室
     * 2.更新实验室信息
     * 3.查看所有实验室
     * 4.删除实验室
     */
    public boolean insertLab(LabEntity labEntity);
    public boolean updateLab(LabEntity labEntity);
//    public List<TableVO> queryTable(TableVO tableVO);

    public boolean deleteLabById(Integer id);
    //    public Integer queryTableCount(TableVO tableVO);
//    public int queryLabTableCount(LabTableVO labTableVO);

}
