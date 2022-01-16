package com.example.lims.common.enums;

import lombok.AllArgsConstructor;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(2000,"操作成功"),

    INVALID_FORMAT(0001,/*"非法格式，如为空或字符串范围超出 "*/null),
    DATEBASE_CONDITION_ERROR(0002,/*重名用户或数据库中没有用户*/null),
    FILTER_CONDITION_ERROR(0003,/*token那块的*/null),
    SYSTEM_ERROR(5000,/*服务器端哪种异常*/null);




    //状态码
    private final Integer code;
    //返回消息
    private final String msg;

    public Integer getCode() {
            return code;
    }

    public String getMsg() {
            return msg;
    }
}
