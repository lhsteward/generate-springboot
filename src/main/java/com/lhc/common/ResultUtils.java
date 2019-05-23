/*
package com.lhc.common;


import com.github.pagehelper.PageInfo;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;


*/
/**
 * @author lihaichao
 * @ClassName ResultUtils
 * @description:
 * @time 2018/11/2911:11
 **//*

public class ResultUtils {

    */
/**
     * @Title: success
     * @Description: 无参成功返回   默认值  code : "0" , msg : "请求成功" , count : 0 , data : null
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:38
     **//*

    public static ResultBody success(){
        return success(0);
    }


    public static ResultBody success(Object object){
        return success(0,object);
    }


    */
/**
     * @Title: success
     * @Description:  有参成功返回   默认值  code : "0" , msg : "请求成功"
     * @param count :  数据条数
     * @param object : 数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:40
     **//*

    public static ResultBody success(Integer count,Object object){
        return new ResultBody().success(count,object);
    }

    */
/**
     * @Title: success
     * @Description:  有参成功返回   默认值  code : "0"
     * @param msg : 提示信息
     * @param count :  数据条数
     * @param object :  数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:40
     **//*

    public static ResultBody success(String msg,Integer count,Object object){
        return new ResultBody().success(msg,count,object);
    }

    */
/**
     * @Title: error
     * @Description: 有参成功返回     默认值  code : "0"
     * @param code :
     * @param object : 数据
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao
     * @date 2018/11/29 11:40
     **//*

    public static ResultBody success(Code code, Object object){
        return new ResultBody().success(code,object);
    }

    */
/**
     * @Title: error
     * @Description: 有参成功返回     默认值  code : "0" data : null
     * @param code : 枚举类代码
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao
     * @date 2018/11/29 11:40
     **//*

    public static ResultBody success(Code code){
        return new ResultBody().success(code);
    }


    */
/**
     * @Title: getDataForLimit
     * @Description: PageHelper分页
     * @param t : list 集合
     * @return com.ys.xlb.bean.ResultBody
     * @author lihaichao 
     * @date 2019-03-14 9:18
     **//*

    public static ResultBody getDataForLimit(List<?>  t){
        return new ResultBody().success((int) new PageInfo(t).getTotal(),t);
    }


    */
/**
     * @Title: error
     * @Description: 错误返回格式     默认值 data : null
     * @param code : 错误代码
     * @param msg : 提示信息
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:40
     **//*

    public static ResultBody error(Integer code,String msg){
        return new ResultBody().error(code,msg);
    }
    
    
    */
/**
     * @Title: error
     * @Description: 错误返回格式     默认值 data : null
     * @param code : 枚举类错误代码
     * @return com.ys.edu.bean.ResultBody
     * @author lihaichao 
     * @date 2018/11/29 11:42
     **//*

    public static ResultBody error(Code code){
        return new ResultBody().error(code);
    }


    */
/**
     * @Title: returnOneData
     * @Description: 增删改 成功条数只能为1时 的判断和返回
     * @param result : 增删改 返回的 成功条数
     * @param object : 需要返回的数据
     * @return com.ys.xlb.bean.ResultBody
     * @author lihaichao
     * @date 2019-03-14 14:04
     **//*

    public static ResultBody returnOneData(int result,Object object){
        if(result != 1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(Code.OPERATE_DATA_ERROR);
        }
        return new ResultBody().success(object);
    }

    */
/**
     * @Title: returnMultiData
     * @Description: 增删改 成功条数非0 的判断和返回
     * @param result : 增删改 返回的 成功条数
     * @param object : 需要返回的数据
     * @return com.ys.xlb.bean.ResultBody
     * @author lihaichao
     * @date 2019-03-14 14:04
     **//*

    public static ResultBody returnMultiData(int result,Object object){
        if(result <= 0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultUtils.error(Code.OPERATE_DATA_ERROR);
        }
        return new ResultBody().success(object);
    }

}*/
