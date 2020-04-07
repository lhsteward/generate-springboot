package ${servicePackage};

import java.util.List;

import org.springframework.stereotype.Repository;
import ${basePackage}.ResultBody;

/**
 * @ClassName BaseService
 * @Description 共用接口Service
 * @Author lihaichao
 * @Date 2018/5/17 17:21
 **/
public interface BaseService<T> {

	/**
	 * @Title: selectAll 
	 * @Description: 查询所有数据list集合
	 * @param t
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午10:32:00
	 */
	ResultBody selectAll(T t);
	
	
	/**
	 * @Title: insertData 
	 * @Description: 添加数据到数据库   不需要主键
	 * @param t
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午10:57:55
	 */
    ResultBody insert(T t);
	

	/**
	 * @Title: update 
	 * @Description: 修改数据   
	 * @param t
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午11:09:52
	 */
    ResultBody update(T t);
	
	
	/**
	 * @Title: delete 
	 * @Description: 删除数据  （默认根据主键删除 也可根据其他列删除 如果不带任何参数 则删除全部 慎用！）
	 * @param t
	 * @return int
	 * @author lihaichao 
	 * @date createTime：2018年5月19日上午11:14:05
	 */
    ResultBody delete(T t);
	

}
