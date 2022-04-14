package com.example.demo.modules.service;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.dao.UserDao;
import com.example.demo.modules.entity.UserEntity;
import com.example.demo.vo.TableVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private TableControlUtil<UserEntity> tableControlUtil;
    private final UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao=userDao;
    }
    @Autowired
    public void setTableHeadUtil(TableControlUtil<UserEntity> tableControlUtil){
        this.tableControlUtil=tableControlUtil;
    }




    @Override
    public UserEntity findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public boolean insert(TableVO tableVO) {
        return userDao.insert(tableVO);
    }

    @Override
    public boolean update(TableVO tableVO) {
        return userDao.update(tableVO);
    }

    @Override
    public boolean deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<TableVO> conditionalQuery(TableVO tableVO) {
        return userDao.conditionalQuery(tableVO);
    }

    @Override
    public Integer conditionalQueryCount(TableVO tableVO) {
        return userDao.conditionalQueryCount(tableVO);
    }


    @Override
    public void setTable(TableVO tableVO) {
        tableControlUtil.setTable(tableVO,userDao);
        tableVO.setTableHead(tableControlUtil.getTableHead(UserDao.class));

    }
}
