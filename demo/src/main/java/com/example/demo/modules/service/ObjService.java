package com.example.demo.modules.service;

import com.example.demo.modules.entity.ObjEntity;
import com.example.demo.vo.ObjSEVO;

public interface ObjService extends Service {
    public void RecordObjByRecordTimeBetween(ObjSEVO objSDVO);
    public Integer RecordObjByRecordTimeBetweenCount(ObjSEVO objSDVO);
    public boolean registerObj(ObjEntity objEntity);
    public boolean nRegisterObj(Integer id);
}
