package com.lhc.common;

import java.io.Serializable;

/**
 * @author lihaichao
 * @ClassName ResultBody
 * @description:  RestFul API 方法返回值格式统一实体类
 * @time 2018/11/29 10:14
 **/
public class ResultBody<T> implements Serializable {

    private static final long serialVersionUID = 694858559908048578L;
    private Integer code;
    private String msg;
    private Integer count = 0;
    private T data;

    public ResultBody(){}

    public ResultBody(Integer code, String msg,Integer count,T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultBody(Integer code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    
    /**
     * @Title: success
     * @Description: 成功  (无参)  默认 code : " 0 "  msg : "请求成功" , count : 0 , data: null
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 10:28
     **/
    public ResultBody success(){
        return success((T) null);
    }

    /**
     * @Title: success
     * @Description:   成功   默认 code : " 0 "  msg : "请求成功"
     * @param count : 数据条数
     * @param data :  数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao
     * @date 2018/11/29 11:46
     **/
    public ResultBody success(Integer count,T data){
        return new ResultBody(0,"请求成功!",count,data);
    }

    /**
     * @Title: success
     * @Description:  成功   默认 code : " 0 "
     * @param msg :  提示信息
     * @param count :  数据条数
     * @param data :   数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:46
     **/
    public ResultBody success(String msg,Integer count,T data){
        return new ResultBody(0,msg,count,data);
    }

    /**
     * @Title: success
     * @Description:  成功   默认 code : " 0 " , msg : "请求成功"
     * @param data :  数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:47
     **/
    public ResultBody success(T data){
        return new ResultBody(0,"请求成功!",data);
    }

    /**
     * @Title: success
     * @Description:  成功   默认 code : " 0 "
     * @param msg :  提示信息
     * @param data :  数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:47
     **/
    public ResultBody success(String msg,T data){
        return new ResultBody(0,msg,data);
    }

    /**
     * @Title: success
     * @Description:  成功   默认 code : " 0 "
     * @param code :  枚举类代码
     * @param data :  数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao
     * @date 2018/11/29 11:47
     **/
    public ResultBody success(Code code,T data){
        return new ResultBody(code.getCode(),code.getMsg(),data);
    }

    /**
     * @Title: success
     * @Description:  成功   默认 code : " 0 "
     * @param code :  枚举类代码
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao
     * @date 2018/11/29 11:47
     **/
    public ResultBody success(Code code){
        return new ResultBody(code.getCode(),code.getMsg(),null);
    }

    
    /**
     * @Title: error
     * @Description:  错误   默认 data : null
     * @param code : 错误代码
     * @param msg : 错误信息
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:48
     **/
    public ResultBody error(Integer code,String msg){
        return new ResultBody(code,msg,null);
    }

    /**
     * @Title: error
     * @Description:  错误   默认 data : null
     * @param code :  枚举类错误代码
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:48
     **/
    public ResultBody error(Code code){
        return new ResultBody(code.getCode(),code.getMsg(),null);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
