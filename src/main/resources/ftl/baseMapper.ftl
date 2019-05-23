package ${daoPackage};

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @ClassName Mapper
 * @Description 共用接口mapper
 * @Author lihaichao
 * @Date 2018/5/17 17:21
 **/
public interface BaseMapper<T> {

	/**
	 * @Title: selectAll 
	 * @Description: 查询所有数据list集合 （可分页）  
	 * @param t
	 * @return List<T>
	 * @author lihaichao 
	 * @date createTime：2018年5月19日上午10:32:00
	 */
	public List<T> selectAll(T t);
	
	
	/**
	 * @Title: insertData 
	 * @Description: 添加数据到数据库   不需要主键
	 * @param t
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午10:57:55
	 */
	public int insert(T t);
	

	
	
	/**
	 * @Title: update 
	 * @Description: 修改数据   
	 * @param p
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午11:09:52
	 */
	public int update(T p);
	
	
	/**
	 * @Title: delete 
	 * @Description: 删除数据  （默认根据主键删除 也可根据其他列删除 如果不带任何参数 则删除全部 慎用！）
	 * @param p
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午11:14:05
	 */
	public int delete(T p);
	
	
	/**
	 * @Title: deleteAll 
	 * @Description: 批量删除  
	 * @param list  id数组集合
	 * @param @return
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午11:14:56
	 */
	public int deleteAll(String[] list);
}
