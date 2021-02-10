package com.zwl.vhrapi.model;

public class RespBean {
    private Integer status;
    private String message;
    private Object obj;
    private String token;

    public static RespBean build(){
        return new RespBean();
    }

    public static RespBean ok(String message){
        return new RespBean(200, message, null, null);
    }

    public static RespBean ok(String message, Object obj, String token){
        return new RespBean(200, message, obj, token);
    }

    public static RespBean ok(String message, Object obj){
        return new RespBean(200, message, obj);
    }

    public static RespBean error(String message){
        return new RespBean(500, message, null, null);
    }

    public static RespBean error(String message, Object obj){
        return new RespBean(500, message, obj, null);
    }



    private RespBean(){}

    private RespBean(Integer status, String message, Object obj, String token) {
        this.status = status;
        this.message = message;
        this.obj = obj;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private RespBean(Integer status, String message, Object obj){
        this.status = status;
        this.message = message;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return message;
    }

    public RespBean setMsg(String msg) {
        this.message = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
