/*
package com.lhc.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

*/
/**
 *@Title JSONUtils.java
 *@description:  JSON工具类
 *@author lihaichao
 *@time 创建时间：2018年6月9日 上午10:39:21
 **//*

public class JSONUtils {


    */
/**
     * @Title: strToObject
     * @Description: json字符串转Object对象
     * @param @param str
     * @param @param clazz
     * @return T
     * @author lihaichao
     * @date createTime：2018年6月9日上午10:40:20
     *//*

    public static<T> T strToObject(String str,Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(str, clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }


    */
/**
     * @Title: objectToStr
     * @Description: Object对象转json字符串
     * @param @param t
     * @return String
     * @author lihaichao
     * @date createTime：2018年6月9日上午10:41:03
     *//*

    public static<T> String objectToStr(T t){
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


    */
/**
     * @Title: strToList
     * @Description: json字符串转list对象集合
     * @param @param str
     * @param @param clazz
     * @return List<T>
     * @author lihaichao
     * @date createTime：2018年6月9日上午10:42:16
     *//*

    public static <T> List<T> strToList(String str, Class<T> clazz) {
        JSONArray json = JSONArray.fromObject(str);
        JSONObject object = null;
        T t = null;
        List<T> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            object = JSONObject.fromObject(json.get(i));
            t = (T) JSONObject.toBean(object, clazz);
            list.add(t);
        }
        return list;
    }

}*/
