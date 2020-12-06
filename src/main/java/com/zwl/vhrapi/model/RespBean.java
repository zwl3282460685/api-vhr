package com.zwl.vhrapi.model;

public class RespBean {
    private Integer status;
    private String message;
    private Object obj;

    public static RespBean ok(String message){
        return new RespBean(200, message, null);
    }

    public static RespBean ok(String message, Object obj){
        return new RespBean(200, message, obj);
    }

    public static RespBean error(String message){
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj);
    }

    private RespBean(){}

    private RespBean(Integer status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
