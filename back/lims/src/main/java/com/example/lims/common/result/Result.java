package com.example.lims.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Result<T> {

    private int code;

    private String msg;

    private T data;

    private static <T>Result<T> response(int code, String msg, T data){
        return new <T>Result<T>(code,msg,data);
    }
    public static  <T>Result<T> success(int code, String msg, T data){
        return response(code,msg,data);
    }

    public static Result fail(int code, String msg){
        return response(code,msg,null);
    }

    


}
