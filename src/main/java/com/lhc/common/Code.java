package com.lhc.common;

/**
 * @author lihaichao
 * @ClassName Code
 * @description: 自定义提示枚举类
 * @time 2018/11/28 18:53
 **/
public enum Code {


    /**
     * @Description:  请求成功状态码   code : 0
     **/
    SUCCESS(0,"请求成功"),
    LOGIN_SUCCESS(0,"登录成功"),
    UPLOAD_SUCCESS(0,"上传成功"),
    PAY_SUCCESS(0,"支付成功"),
    ON_LINE(0,"在线"),

    /**
     * @Description: 请求失败  code  -1
     **/
    ERROR(-1,"请求失败"),
    UPLOAD_ERROR(-1,"上传文件失败"),
    PAY_ERROR(-1,"支付失败");


    private Integer code;
    private String msg;

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

    Code(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
