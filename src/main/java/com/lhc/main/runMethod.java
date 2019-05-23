package com.lhc.main;

import com.lhc.utils.DatabaseUtils;

/** 
*@Title runMethod.java 
*@description:  运行方法
*@author lhc
*@time 创建时间：2018年7月30日 上午9:44:57  
**/
public class runMethod {

	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println("========正在生成=========");
		DatabaseUtils db = new DatabaseUtils();
		db.generateModel();//生成实体类
		db.generateInterface();//生成dao,service,controller
		//db.generateListHTML(); //生成HTML和JS文件
		//db.generateSSMProperties();//生成配置文件
		System.out.println("文件已生成");
	}
	
}
