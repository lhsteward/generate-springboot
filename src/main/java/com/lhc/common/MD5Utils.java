package com.lhc.common;

import java.security.MessageDigest;

/** 
*@Title MD5Utils.java 
*@description:  MD5加密工具类
*@author lihaichao
*@time 2017年12月21日 上午10:37:25 
**/
public class MD5Utils {

		private final static String MD_SALT = "admin";

	/**
	 * @Title: EncoderByMd5
	 * @Description:  MD5 加密
	 * @param inStr :  需要加密的字符串
	 * @return java.lang.String
	 * @author lihaichao 
	 * @date 2018/11/29 16:23
	 **/
	public final static String EncoderByMd5(String inStr) {
		 inStr += MD_SALT;
		 MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();    
    }
	
     /**
      * @Title: KL
      * @Description: 可逆的加密算法  两次可逆
	  * @param inStr : 需要加密的字符串
      * @return java.lang.String
      * @author lihaichao 
      * @date 2018/11/29 16:35
      **/
	 public static String KL(String inStr) {   
		  // String s = new String(inStr);   
		  char[] a = inStr.toCharArray();   
		  for (int i = 0; i < a.length; i++) {   
		   a[i] = (char) (a[i] ^ 't');   
		  }   
		  String s = new String(a);   
		  return s;   
		 } 
	
	
	/**
	 * @Title: DecodeByMd5
	 * @Description:  解密 MD5
	 * @param inStr : 需要解密的 MD5 字符串
	 * @return java.lang.String
	 * @author lihaichao 
	 * @date 2018/11/29 16:25
	 **/
	public static String DecodeByMd5(String inStr) {   
		  char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
	        }  
	        String s = new String(a);  
	        return s;    
		 }

	public static void main(String[] args) {
		System.out.println(EncoderByMd5("admin"));
	}


}
