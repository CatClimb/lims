package com.example.demo.modules.service;

import com.example.demo.modules.dao.Dao;
import com.example.demo.vo.TableVO;

import java.util.List;

public interface Service extends Dao {
  public void  setTable(TableVO tableVO);
}
