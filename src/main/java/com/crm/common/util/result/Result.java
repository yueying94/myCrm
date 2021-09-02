package com.crm.common.util.result;


import lombok.Data;

import java.io.Serializable;

/**
 * 封装结果集，后端--→前端
 */

@Data
public class Result implements Serializable {

    private int code;   //200正常，其他异常
    private String msg;
    private Object data;

    //静态方法，便于调用
    public static Result succ(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result fail(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
