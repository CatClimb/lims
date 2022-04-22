package com.example.demo.modules.service;

import com.example.demo.vo.ObjSEVO;

public interface ObjService extends Service {
    public void RecordObjByRecordTimeBetween(ObjSEVO objSDVO);
    public Integer RecordObjByRecordTimeBetweenCount(ObjSEVO objSDVO);
}
