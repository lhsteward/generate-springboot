/*
package com.lhc.common;

import com.ys.xlb.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

*/
/**
 * @author lihaichao
 * @ClassName GlobalExceptionHandler
 * @description: 自定义异常Handler控制类
 * @time 2018/11/2818:18
 **//*

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(GlobalException.class)
    public ResultBody handlerGlobalException(GlobalException e) {
        e.printStackTrace();
        log.error("\r\n异常信息 : \r\n[\r\n 错误代码 >>>>>>> {} , 错误信息 >>>>>>> {} \r\n]." , e.getCode(),e.getE());//e.getStackTrace()[0]
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public  ResultBody handleException(Exception e) {
        e.printStackTrace();
        log.error("\r\n系统系统异常 >>>>>> {} , \r\n错误信息 >>>>>>> {} ,\r\n {} \r\n" ,e.getMessage(), getExceptionAllinfo(e));
        return ResultUtils.error(Code.EXCEPTION_ERROR);
    }


    //验证异常
    @ExceptionHandler(value = {BindException.class , MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultBody handleBindException(BindException e) throws BindException {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append(" =[ ").append(fieldError.getRejectedValue()).append(" ] ")
                .append(fieldError.getDefaultMessage());
        log.error("\r\n参数验证异常 >>>>>> BindException : \n {} ." ,sb.toString());
        log.error("\r\n参数验证异常 >>>>>> 错误信息 : " , e);
        return ResultUtils.error(50003,sb.toString());
    }

    public String getExceptionAllinfo(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return ret;
    }

}
*/
