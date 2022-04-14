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
    public  <t> List<String[]> getTableHead(Class<t> classTmp){
        List<String[]> strings = new ArrayList<>();
        Field[] declaredFields = classTmp.getDeclaredFields();
        int length = declaredFields.length;
        for (int i = 0; i < length; i++) {
            String[] str = new String[2];
            str[0] = declaredFields[i].getName();
            str[1] = declaredFields[i].getAnnotation(Alias.class).value();
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
        tableVO.setStart((tableVO.getPage()-1)* tableVO.getPageSize());
        tableVO.setTableData(dao.conditionalQuery(tableVO));
        log.info(dao.conditionalQuery(tableVO).toString());
        tableVO.setCount(dao.conditionalQueryCount(tableVO));
        log.info(dao.conditionalQueryCount(tableVO).toString());
    }

}
