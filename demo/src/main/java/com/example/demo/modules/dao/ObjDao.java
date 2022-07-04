package com.example.demo.modules.dao;

import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.vo.ObjSEVO;
import com.example.demo.vo.TableVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ObjDao extends Dao {
    public List<TableVO> RecordObjByRecordTimeBetween(ObjSEVO objSDVO);
    public Integer RecordObjByRecordTimeBetweenCount(ObjSEVO objSDVO);
    public boolean updateByIdFixationObjStatus(ObjEntity objEntity);


}
