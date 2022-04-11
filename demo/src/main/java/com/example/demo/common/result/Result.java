package com.example.demo.common.result;

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

    private static <T> Result<T> response(int code, String msg, T data){
        return new Result<T>(code,msg,data);
    }
    public static <T> Result<T> success(String msg){
        return success(msg,null);
    }
    public static  <T> Result<T> success( String msg, T data){
        return response(ResultEnum.SUCCESS.getCode(), msg,data);
    }
    public static <T> Result<T> fail(String msg) {
        return fail(ResultEnum.INVALID.getCode( ), msg);
    }
    public static <T> Result<T> fail(int code, String msg){
        return response(code,msg,null);
    }

    


}
