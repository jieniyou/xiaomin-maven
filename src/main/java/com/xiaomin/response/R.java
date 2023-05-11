package com.xiaomin.response;

public class R {
    /**
     * 状态码
     */
    private int code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;
    /**
     * 数据的条数（查询的时候）
     */
    private int count;
    /**
     * 代表成功的请求
     */
    public static R ok(String msg){
        return ok(msg, null);
    }
    public static R ok(String msg,Object data){
        return ok(200,msg,data,0);
    }
    public static R ok(int code,String msg,Object data,int count){
        R r=new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        r.setCount(count);
        return r;
    }
    /**
     * 代表失败的请求
     */
    public static R no(String msg){
        return no(500,msg);
    }
    public static R no(int code,String msg){
        R r=new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
    //禁用用户新建对象
    private R(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
