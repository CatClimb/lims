package com.example.demo.modules.service;

import com.example.demo.vo.TableVO;

public interface DeviceService extends Service{
    public float devicePriceSum();
    public void mulTableQueryDevLend(TableVO tableVO);

}
