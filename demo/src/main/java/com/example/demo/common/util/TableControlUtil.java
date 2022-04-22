package com.example.demo.common.util;

import com.example.demo.common.Alias;
import com.example.demo.modules.dao.Dao;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Component
@Slf4j
public class TableControlUtil<T> {
    public  <t> List<String[]> getTableHead(Class<t> classTmp,int tmp){
        List<String[]> strings = new ArrayList<>();
        Field[] declaredFields = classTmp.getDeclaredFields();
        int length = declaredFields.length;
        for (int i = tmp; i < length; i++) {
            String[] str = new String[2];
            Alias annotation = declaredFields[i].getAnnotation(Alias.class);
            if(annotation!=null){
                str[0] = declaredFields[i].getName();
                str[1] = annotation.value();
            }
            strings.add(str);
        }
        return strings;
    }
    public void setTable(TableVO tableVO, Dao dao){
        if(tableVO.getPage()<=0){
            tableVO.setPage(1);
        }
        if (tableVO.getPageSize()<=0){
            tableVO.setPage(10);
        }
        int start=(tableVO.getPage()-1)* tableVO.getPageSize();
        int count=dao.conditionalQueryCount(tableVO);
        if(start>=count){
            tableVO.setPage(1);
            start=(tableVO.getPage()-1)* tableVO.getPageSize();
        }
        tableVO.setStart(start);
        tableVO.setCount(count);
        tableVO.setTableData(dao.conditionalQuery(tableVO));

    }

}
