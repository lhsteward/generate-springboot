package ${servicePackage};

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import ${daoPackage}.${tableName}Mapper;
import ${modelPackage}.${tableName};
import com.github.pagehelper.PageHelper;



/** 
*@Title ${tableName}ServiceImpl.java
*@description:  ${tableName}ServiceImpl
*@author lhc
**/
@Service
@Transactional
public class ${tableName}ServiceImpl implements ${tableName}Service{

	@Resource
	private ${tableName}Mapper mapper;
	
	
	/**
	 * @Title: selectAll 
	 * @Description: 查询所有    参数为空默认查询所有
	 * @param t
	 * @author lhc
	 */
	@Override
	public ResultBody selectAll(${tableName} t){
		PageHelper.startPage(t.getPage()== null ? 0 : t.getPage(), t.getLimit() == null ? 0 : t.getLimit());
		return ResultUtils.getDataForLimit(mapper.selectAll(t));
	}
	
	
	/**
	 * @Title: insert
	 * @Description: 添加
	 * @param t
	 * @author lhc
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class, GlobalException.class})
	public ResultBody insert(${tableName} t){
		if(t.getId() == null || t.getId().equals("")){
            return ResultUtils.error(Code.PARAM_ERROR);
        }
		String id = StringUtils.getNanotime();
		t.setId(id);
 		return ResultUtils.returnOneData(mapper.insert(t),null);
	}
	
	
	/**
	 * @Title: update
	 * @Description: 修改
	 * @param t
	 * @author lhc
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class, GlobalException.class})
	public ResultBody update(${tableName} t){
		if(t.getId() == null || t.getId().equals("")){
            return ResultUtils.error(Code.PARAM_ERROR);
        }
		return ResultUtils.returnOneData(mapper.update(t),null);
	}
	
	
	/**
	 * @Title: delete
	 * @Description: 删除  没有参数将删除全部(推荐传入主键)
	 * @param t
	 * @author lhc
	 */
	@Override
	@Transactional(rollbackFor = {Exception.class, GlobalException.class})
	public ResultBody delete(${tableName} t){
		if(t == null || t.getId() == null || t.getId().equals("")){
            return ResultUtils.error(Code.PARAM_ERROR);
        }
		return ResultUtils.returnOneData(mapper.delete(t),null);
	}
	
}
