package com.lhc.common;

/**
 * @author lihaichao
 * @ClassName Code
 * @description: 自定义提示枚举类
 * @time 2018/11/28 18:53
 **/
public enum Code {


    /**
     * @Description:  请求成功状态码   code : "0"
     **/
    SUCCESS(0,"请求成功"),
    LOGIN_SUCCESS(0,"登录成功"),
    UPLOAD_SUCCESS(0,"上传成功"),
    PAY_SUCCESS(0,"支付成功"),
    ON_LINE(0,"在线"),

    /**
     * @Description: 登录 异常状态码  10 开头
     **/
    LOGIN_ERROR(10001,"登录异常"),
    USERORPASSWORD_ERROR(10002,"账号或密码错误"),
    BAN_USER_LOGIN_ERROR(10003,"该用户已禁止登录"),
    REDIS_CONNECTION_ERROR(10004,"Redis连接失败"),

    /**
     * @Description:  业务异常  20 开头
     **/

    RESOLVER_ERROR(20001,"解析错误"),
    GET_DATA_ERROR(20002,"获取数据失败"),
    OPERATE_DATA_ERROR(20003,"操作数据失败"),
    DOWN_LINE(20004,"离线"),
    REFUND_ERROR(20005,"退款失败"),
    UPLOAD_ERROR(20006,"上传失败"),
    GOODS_NORM_NOT_FOUND(20009,"该商品规格已失效"),

    GET_ORDER_ERROR(20010,"订单信息错误"),
    STOCK_NOT_ENOUGH(20011,"库存不足"),
    UPDATE_STOCK_ERROR(20012,"更新库存失败"),

    /**
     * @Description:  系统异常  30 开头
     **/

    SYSTEM_ERROR(30001,"系统错误"),
    BAD_REQUEST(30002,"请求错误"),
    METHOD_NOT_ALLOWED(30003,"不合法的请求方式"),
    EXCEPTION_ERROR(30500,"系统 500 异常"),
    NOT_FOUND(30404,"系统 404 异常"),
    API_REQUEST_TOO_MUCH(30405,"请求过于频繁"),

    /**
     * @Description:  数据库异常  40 开头
     **/
    DATABASE_ERROR(40001,"数据库异常"),
    DATA_ERROR(40002,"数据错误或不存在"),
    DATA_HAS_ERROR(40003,"数据已存在"),
    UNAME_HAS_ERROR(40004,"用户名已存在"),
    NAME_HAS_ERROR(40005,"名称已存在"),

    /**
     * @Description:  参数异常  50 开头
     **/
    PARAM_ERROR(50001,"参数错误"),
    FILE_PATH_ERROR(50002,"文件路径错误"),
    PARAM_VALIDA_ERROR(50003,"参数验证失败"),

    /**
     * @Description: 微信
     **/
    WX_CODE_NOT_NULL(70001,"微信登录凭证不能为空"),
    WX_DECODE_ERROR(70002,"解密失败"),
    GET_SESSION_KEY_ERROR(70003,"获取session_key失败"),
    DATA_RESOLVER_ERROR(70004,"数据解析失败");



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
