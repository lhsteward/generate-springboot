package com.lhc.utils;  
/** 
*@Title DatabaseUtils.java 
*@description:  
*@author lihaichao
*@time 创建时间：2018年7月25日 下午4:00:30 
**/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DatabaseUtils {
	
	private final static Logger logger = Logger.getLogger(DatabaseUtils.class);
	
	
	/**
	 * @Title: getConnection 
	 * @Description: 获取数据库连接
	 * @return Connection
	 * @author lhc 
	 * @date createTime：2018年7月25日下午4:21:22
	 */
	protected static Connection getConnection(){
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Connection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(is);
			if (properties.getProperty("jdbc.type").equalsIgnoreCase("mysql")) {
				Class.forName(properties.getProperty("jdbc.driver"));
				connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
			}else{
				logger.error("数据库有误!此配置只支持MySQL数据库!");
			}
		} catch (IOException e) {
			logger.error("读取配置文件异常!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.error("加载数据库驱动失败!");
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("连接MySQL失败,请检查参数配置");
			e.printStackTrace();
		}
		return connection;
	}
	
	
	/**
	 * @Title: closeConn 
	 * @Description: 关闭数据库连接
	 * @param @param conn
	 * @param @param pstmt
	 * @param @param rs
	 * @return void
	 * @author lhc 
	 */
	protected static void closeConn(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");
	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	
	
	/**
	 * @Title: getModel 
	 * @Description: 生成实体类
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月27日上午9:17:39
	 */
	protected static void getModel(String tableName,List<String> columnList,List<String> typeList,List<String> commentList) throws IOException{
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		properties.load(is);
		//创建Freemarker配置实例
		Map<String, Object> map = new HashMap<String, Object>();
        Configuration cfg = new Configuration();
        //加载模板文件
        cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
		Template t1 = cfg.getTemplate("model.ftl");
		Template t2 = cfg.getTemplate("page.ftl");
		String sBuffer = "\n";
		String str = "\n";
		if(tableName.length() > 3 && tableName.substring(0,4).equalsIgnoreCase("sys_")){
			tableName = StringUtils.upperCase(lineToHump(tableName.substring(4)));
		}else{
			tableName = StringUtils.upperCase(lineToHump(tableName));
		}
		for (int i = 0; i < columnList.size(); i++) {
				map.put("tableName", tableName);
				map.put("modelPackage", properties.getProperty("modelPackage"));
				map.put("typeList", typeList);
				//注释
				String comment = commentList.get(i).equals("") ? columnList.get(i) : commentList.get(i) ;
				sBuffer +="	private "+typeList.get(i)+" "+columnList.get(i)+";  //"+comment+"\n";
				/*String pString=StringUtils.toLowerCaseFirstOne(columnList.get(i));
				str += "	public "+typeList.get(i)+" get"+StringUtils.upperCase(columnList.get(i))+"() {\n"
						+"		return "+pString+";\n	}\n\n";
				str += "	public void set"+StringUtils.upperCase(columnList.get(i))+"("+typeList.get(i)+" "+pString+") {\n"
						+"		this."+pString+"="+pString+";\n	}\n\n";*/
		}
		map.put("property", sBuffer+=str);
		Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("modelPackage").replaceAll("\\.", "\\\\"), tableName+".java"));
		Writer out1 = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("modelPackage").replaceAll("\\.", "\\\\"), "Page.java"));
		try {
			t1.process(map, out);
			t2.process(null, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out1.flush();
		}
	}

	//生成HTML和JS文件
	public static void generateListHTML(){
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			//创建Freemarker配置实例
			Configuration cfg = new Configuration();
			//加载模板文件
			cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
			Template t = cfg.getTemplate("listHtml.ftl");
			Template t1 = cfg.getTemplate("list.ftl");
			Template t2 = cfg.getTemplate("addHtml.ftl");
			Template t3 = cfg.getTemplate("add.ftl");
			Template t4= cfg.getTemplate("editHtml.ftl");
			Template t5 = cfg.getTemplate("edit.ftl");

			Writer out = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "list.html"));
			Writer out1 = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "list.js"));
			Writer out2 = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "add.html"));
			Writer out3 = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "add.js"));
			Writer out4 = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "edit.html"));
			Writer out5 = new OutputStreamWriter(generateFile(properties.getProperty("htmlAndJs"), "edit.js"));
			try {
				t.process(null, out);
				t1.process(null, out1);
				t2.process(null, out2);
				t3.process(null, out3);
				t4.process(null, out4);
				t5.process(null, out5);
			} catch (TemplateException e) {
				e.printStackTrace();
			}finally {
				out.flush();
				out1.flush();
				out2.flush();
				out3.flush();
				out4.flush();
				out5.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @Title: generateModel 
	 * @Description: 获取表内容 生成实体类
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月27日上午9:17:39
	 */
	public static void generateModel(){
		Connection connection = getConnection();
		List<String> tables = new ArrayList<String>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		if (connection != null) {
			try {
				DatabaseMetaData dm = connection.getMetaData();
				Properties properties = new Properties();
				properties.load(is);
				ResultSet rs = dm.getTables(connection.getCatalog(), properties.getProperty("jdbc.username"), null, new String[]{"TABLE"});
				while(rs.next()) {
					tables.add(StringUtils.upperCase(rs.getString("TABLE_NAME")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (String tableName : tables) {
				String sql = "SELECT * FROM "+tableName;
				List<String> column = new ArrayList<String>();
				List<String> columnType = new ArrayList<String>();
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
	                ResultSetMetaData meta = rs.getMetaData();
	                int columeCount = meta.getColumnCount();
	                for (int i = 1; i < columeCount + 1; i++) {
	                	column.add(meta.getColumnName(i));
	                	columnType.add(sqlTypesToJava(meta.getColumnType(i)));
	                }
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
				List<String> comment = new ArrayList<String>();
				String sql1 = "SHOW FULL COLUMNS FROM "+tableName;
				try {
					PreparedStatement ps = connection.prepareStatement(sql1);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						comment.add(rs.getString("Comment"));
		            }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					getModel(tableName, column, columnType ,comment);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * @Title: generateInterface 
	 * @Description: 生成dao层 service,controller等
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月27日下午2:25:02
	 */
	public static void generateInterface(){
		Connection connection = getConnection();
		List<String> tables = new ArrayList<String>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//创建Freemarker配置实例
        Configuration cfg = new Configuration();
        //加载模板文件
        cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
		try {
			Template t1 = cfg.getTemplate("interface.ftl");
			if(connection != null){
				DatabaseMetaData dm;
				try {
					dm = connection.getMetaData();
					ResultSet rs = dm.getTables(connection.getCatalog(), properties.getProperty("jdbc.username"), null, new String[]{"TABLE"});
					while(rs.next()) {
						tables.add(StringUtils.upperCase(rs.getString("TABLE_NAME")));
					}
					//生成 统一集成接口
					generateBaseMapper(properties.getProperty("daoPackage"));
					generateBaseService(properties.getProperty("servicePackage"),properties.getProperty("basePackage"));
					for (int i = 0; i < tables.size(); i++) {
						String mapperName = "";
						if(tables.get(i).length() > 3 && tables.get(i).substring(0,4).equalsIgnoreCase("sys_")){
							mapperName = StringUtils.upperCase(lineToHump(tables.get(i).substring(4)));
						}else{
							mapperName = StringUtils.upperCase(lineToHump(tables.get(i)));
						}
						Map<String, Object> map = new HashMap<>();
						map.put("modelPackage", properties.getProperty("modelPackage"));
						map.put("daoPackage", properties.getProperty("daoPackage"));
						map.put("tableName", mapperName);
						Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("daoPackage").replaceAll("\\.", "\\\\"), mapperName+"Mapper.java"));
						try {
							t1.process(map, out);
							generateMapper(connection,  tables.get(i), properties.getProperty("modelPackage"), properties.getProperty("daoPackage"));
							generateService(tables.get(i), properties.getProperty("daoPackage"), properties.getProperty("modelPackage"), properties.getProperty("servicePackage"),properties.getProperty("basePackage"));
							generateServiceImpl(tables.get(i), properties.getProperty("daoPackage"), properties.getProperty("modelPackage"), properties.getProperty("servicePackage"),properties.getProperty("basePackage"));
							generateController(tables.get(i), properties.getProperty("servicePackage"), properties.getProperty("modelPackage"),properties.getProperty("controllerPackage"));
						} catch (TemplateException e) {
							e.printStackTrace();
						}finally {
							out.flush();
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	protected static void generateBaseMapper(String daoPackage){
		Map<String, Object> map = new HashMap<String, Object>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		Configuration cfg = new Configuration();
		//加载模板文件
		cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
		try {
			properties.load(is);
			map.put("daoPackage", daoPackage);
			Template t1 = cfg.getTemplate("baseMapper.ftl");
			Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("daoPackage").replaceAll("\\.", "\\\\"), "BaseMapper.java"));
			t1.process(map, out);
			out.flush();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}


	protected static void generateBaseService(String servicePackage,String basePackage){
		Map<String, Object> map = new HashMap<String, Object>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		Configuration cfg = new Configuration();
		//加载模板文件
		cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
		try {
			properties.load(is);
			map.put("servicePackage", servicePackage);
			map.put("basePackage", basePackage);
			Template t1 = cfg.getTemplate("baseService.ftl");
			Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("servicePackage").replaceAll("\\.", "\\\\"), "BaseService.java"));
			t1.process(map, out);
			out.flush();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * @Title: generateMapper 
	 * @Description: 生成mapper文件
	 * @param @param connection 数据库连接
	 * @param @param tableName 表名
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月27日下午3:31:43
	 */
	protected static void generateMapper(Connection connection,String tableName,String modelPackage,String daoPackage){
		Map<String, Object> map = new HashMap<>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			Configuration cfg = new Configuration();
			//加载模板文件
			cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
			try {
				Template t1 = cfg.getTemplate("mapperXML.ftl");
				String sql = "SELECT * FROM "+tableName;
				List<String> column = new ArrayList<>();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columeCount = meta.getColumnCount();
				for (int i = 1; i < columeCount + 1; i++) {
					column.add(meta.getColumnName(i));
				}
				String newTableName="";
				if(tableName.length() > 3 && tableName.substring(0,4).equalsIgnoreCase("sys_")){
					newTableName = StringUtils.upperCase(lineToHump(tableName.substring(4)));
				}else{
					newTableName = StringUtils.upperCase(lineToHump(tableName));
				}
				map.put("column", column);
				map.put("tableName", newTableName);
				map.put("tableNameL", StringUtils.toLowerCaseFirstOne(newTableName));
				map.put("modelPackage", modelPackage);
				map.put("daoPackage", daoPackage);
				Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("mapperPackage").replaceAll("\\.", "\\\\"), newTableName+"Mapper.xml"));
				try {
					t1.process(map, out);
					out.flush();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}


	/**
	 * @Title: generateService
	 * @Description: 生成service
	 * @param @param tableName table表名
	 * @param @param daoPackage dao层包名
	 * @param @param modelPackage model层包名
	 * @param @param basePackage 基础包路径
	 * @return void
	 * @author lhc
	 * @date createTime：2018年7月27日下午6:02:24
	 */
	protected static void generateService(String tableName,String daoPackage,String modelPackage,String servicePackage,String basePackage){
		Map<String, Object> map = new HashMap<>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			Configuration cfg = new Configuration();
			//加载模板文件
			cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
			try {
				Template t1 = cfg.getTemplate("service.ftl");
				String newTableName="";
				if(tableName.length() > 3 && tableName.substring(0,4).equalsIgnoreCase("sys_")){
					newTableName = StringUtils.upperCase(lineToHump(tableName.substring(4)));
				}else{
					newTableName = StringUtils.upperCase(lineToHump(tableName));
				}
				map.put("tableName", newTableName);
				map.put("tableNameL", StringUtils.toLowerCaseFirstOne(newTableName));
				map.put("modelPackage", modelPackage);
				map.put("servicePackage", servicePackage);
				map.put("daoPackage", daoPackage);
				map.put("basePackage", basePackage);
				Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("servicePackage").replaceAll("\\.", "\\\\"), newTableName+"Service.java"));
				try {
					t1.process(map, out);
					out.flush();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * @Title: generateServiceImpl
	 * @Description: 生成serviceImpl
	 * @param @param tableName table表名
	 * @param @param daoPackage dao层包名
	 * @param @param modelPackage model层包名
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月27日下午6:02:24
	 */
	protected static void generateServiceImpl(String tableName,String daoPackage,String modelPackage,String servicePackage,String basePackage){
		Map<String, Object> map = new HashMap<>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			Configuration cfg = new Configuration();
			//加载模板文件
			cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
			try {
				Template t1 = cfg.getTemplate("serviceImpl.ftl");
				String newTableName="";
				if(tableName.length() > 3 && tableName.substring(0,4).equalsIgnoreCase("sys_")){
					newTableName = StringUtils.upperCase(lineToHump(tableName.substring(4)));
				}else{
					newTableName = StringUtils.upperCase(lineToHump(tableName));
				}
				map.put("tableName", newTableName);
				map.put("tableNameL", StringUtils.toLowerCaseFirstOne(newTableName));
				map.put("modelPackage", modelPackage);
				map.put("servicePackage", servicePackage);
				map.put("daoPackage", daoPackage);
				map.put("basePackage", basePackage);
				Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("serviceImplPackage").replaceAll("\\.", "\\\\"), newTableName+"ServiceImpl.java"));
				try {
					t1.process(map, out);
					out.flush();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * @Title: generateController 
	 * @Description: 生成controller
	 * @param @param tableName 表名
	 * @param @param servicePackage service层包名
	 * @param @param modelPackage model层包名
	 * @param @param controllerPackage controller层包名
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月28日下午4:20:24
	 */
	protected static void generateController(String tableName,String servicePackage,String modelPackage,String controllerPackage){
		Map<String, Object> map = new HashMap<>();
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			Configuration cfg = new Configuration();
			//加载模板文件
			cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
			try {
				Template t1 = cfg.getTemplate("controller.ftl");
				String newTableName="";
				if(tableName.length() > 3 && tableName.substring(0,4).equalsIgnoreCase("sys_")){
					newTableName = StringUtils.upperCase(lineToHump(tableName.substring(4)));
				}else{
					newTableName = StringUtils.upperCase(lineToHump(tableName));
				}
				map.put("tableName", newTableName);
				map.put("modelPackage", modelPackage);
				map.put("servicePackage", servicePackage);
				map.put("controllerPackage", controllerPackage);
				map.put("tableNameL", StringUtils.toLowerCaseFirstOne(newTableName));
				Writer out = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("controllerPackage").replaceAll("\\.", "\\\\"), newTableName+"Controller.java"));
				try {
					t1.process(map, out);
					out.flush();
				} catch (TemplateException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * @Title: generateSSMProperties 
	 * @Description: 生成配置文件
	 * @return void
	 * @author lhc 
	 * @date createTime：2018年7月28日下午4:19:58
	 */
	public static void generateSSMProperties(){
		InputStream is= DatabaseUtils.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			Map<String, Object> map = new HashMap<>();
			Configuration cfg = new Configuration();
	        //加载模板文件
	        cfg.setClassForTemplateLoading(DatabaseUtils.class, "/ftl");
	        Template t = cfg.getTemplate("applicationContext.ftl");
	        map.put("daoPackage", properties.getProperty("daoPackage"));
	        map.put("servicePackage", properties.getProperty("servicePackage"));
	        Writer out = new OutputStreamWriter(generateFile(properties.getProperty("resourcesPath")+"\\spring", "applicationContext.xml"));
			try {
				t.process(map, out);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
	        Template t1 = cfg.getTemplate("spring-mvc.ftl");
	        Map<String, Object> map1 = new HashMap<>();
	        map1.put("controllerPackage", properties.getProperty("controllerPackage"));
	        Writer out1 = new OutputStreamWriter(generateFile(properties.getProperty("resourcesPath")+"\\spring", "spring-mvc.xml"));
			try {
				t1.process(map1, out1);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			Template t2 = cfg.getTemplate("mybatis.cfg.ftl");
			Writer out2 = new OutputStreamWriter(generateFile(properties.getProperty("resourcesPath")+"\\mybatis", "mybatis.cfg.xml"));
			try {
				t2.process(null, out2);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			Template t3 = cfg.getTemplate("dbproperties.ftl");
			Writer out3 = new OutputStreamWriter(generateFile(properties.getProperty("resourcesPath")+"\\properties", "db.properties"));
			try {
				t3.process(null, out3);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			Template t4 = cfg.getTemplate("log4jproperties.ftl");
			Writer out4 = new OutputStreamWriter(generateFile(properties.getProperty("resourcesPath")+"\\properties", "log4j.properties"));
			try {
				t4.process(null, out4);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			Template t5 = cfg.getTemplate("loginInterceptor.ftl");
			Writer out5 = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("interceptorPackage").replaceAll("\\.", "\\\\"), "loginInterceptor.java"));
			Map<String, Object> map5 = new HashMap<>();
			map5.put("interceptorPackage", properties.getProperty("interceptorPackage"));
			map5.put("modelPackage", properties.getProperty("modelPackage"));
			try {
				t5.process(map5, out5);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			Template t6 = cfg.getTemplate("loginFilter.ftl");
			Writer out6 = new OutputStreamWriter(generateFile(properties.getProperty("filePath")+"\\"+properties.getProperty("filterPackage").replaceAll("\\.", "\\\\"), "loginFilter.java"));
			Map<String, Object> map6 = new HashMap<>();
			map6.put("filterPackage", properties.getProperty("filterPackage"));
			map6.put("modelPackage", properties.getProperty("modelPackage"));
			try {
				t6.process(map6, out6);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			
			Template t7 = cfg.getTemplate("web.ftl");
			Writer out7 = new OutputStreamWriter(generateFile(properties.getProperty("webPath"), "web.xml"));
			Map<String, Object> map7 = new HashMap<>();
			map7.put("filterPackage", properties.getProperty("filterPackage"));
			map7.put("projectName", properties.getProperty("projectName"));
			try {
				t7.process(map7, out7);
				out.flush();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeConn(getConnection(), null, null);
		}
	}
	
	
	/**
	 * @Title: generateFile 
	 * @Description: 创建文件
	 * @param @return
	 * @return boolean
	 * @author lhc 
	 * @date createTime：2018年7月28日上午11:32:10
	 */
	protected static FileOutputStream generateFile(String path,String fileName) {
		File parentPath=new File(path);
		if(!parentPath.exists()){
			parentPath.mkdirs();
		}
		FileOutputStream out = null;
		String realPath = path+"//"+fileName;
		File realFile=new File(realPath);
		if(!realFile.exists()){
			try {
				realFile.createNewFile();
				out = new FileOutputStream(realFile,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			realFile.delete();
			try {
				realFile.createNewFile();
				out = new FileOutputStream(realFile,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out;
	}
	
	
	
	/**
	 * @Title: sqlTypesToJava 
	 * @Description: 数据库字段类型值 对应的java类型
	 * @param @param code
	 * @return String
	 * @author lhc 
	 */
	protected static String sqlTypesToJava(Integer code){
		Integer[] IntegerType = {4,-6,5};
		Integer[] StringType = {12,1,-1};
		Integer[] DoubleType = {2,6};
		Integer[] TimestampType = {93};
		Integer[] BigDecimalType = {3};
		Integer[] DateType = {91};

		String type = "";
		if(StringUtils.containsArr(IntegerType, code)){
			type="Integer";
		}else if(StringUtils.containsArr(StringType, code)){
			type="String";
		}else if(StringUtils.containsArr(DoubleType, code)){
			type="double";
		}else if(StringUtils.containsArr(TimestampType, code)){
			type="Timestamp";
		}else if(StringUtils.containsArr(BigDecimalType, code)){
			type="BigDecimal";
		}else if(StringUtils.containsArr(DateType, code)){
			type="Date";
		}else{
			type="String";
		}
		return type;
	}
	
}
