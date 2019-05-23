package ${controllerPackage};

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.servlet.ModelAndView;

import ${modelPackage}.${tableName};
import ${servicePackage}.${tableName}Service;

/** 
*@Title ${tableName}Controller.java 
*@description:  ${tableName}controller
*@author lhc
**/
@RestController
@RequestMapping(value="${tableNameL}")
public class ${tableName}Controller {

	@Resource
	private ${tableName}Service service;
	ModelAndView mav = new ModelAndView();

	//列表页
	@RequestMapping("list.html")
    public ModelAndView list(){
        mav.setViewName("${tableNameL}/list");
        return mav;
    }

	//新增页
	@RequestMapping("add.html")
    public ModelAndView addInput(){
        mav.setViewName("${tableNameL}/add");
        return mav;
    }

	//修改页
    @RequestMapping("edit.html")
    public ModelAndView editInput(String id){
        mav.addObject("id",id);
        mav.setViewName("${tableNameL}/edit");
        return mav;
    }

	/**
	 * @Title: selectAll 
	 * @Description: 查询所有信息
	 * @param ${tableName}
	 * @author lhc
	 */
	@RequestMapping(value = "selectAll",method={RequestMethod.GET,RequestMethod.POST})
	public ResultBody selectAll(${tableName} t){
		return service.selectAll(t);
	}


	/**
	 * @Title: insert
	 * @Description: 插入
	 * @param ${tableName}
	 * @author lhc
	 */
	@RequestMapping(value = "insert",method={RequestMethod.GET,RequestMethod.POST})
	public ResultBody insert(${tableName} t){
		return service.insert(t);
	}


	/**
	 * @Title: update 
	 * @Description: 修改
	 * @param ${tableName}
	 * @author lhc
	 */
	@RequestMapping(value = "update",method={RequestMethod.GET,RequestMethod.POST})
	public ResultBody update(${tableName} t){
		return service.update(t);
	}
	
	
	/**
	 * @Title: delete 
	 * @Description: 删除 没有参数将删除全部(推荐传入主键)
	 * @param ${tableName}
	 * @author lhc
	 */
	@RequestMapping(value = "delete",method={RequestMethod.GET,RequestMethod.POST})
	public ResultBody delete(${tableName} t){
		return service.delete(t);
	}


	/**
	 * @Title: deleteAll
	 * @Description: 批量删除
	 * @param ids  id字符串
	 * @author lihaichao
	 * @date createTime：2018年5月19日上午11:14:56
	 */
	@RequestMapping(value = "deleteAll",method={RequestMethod.GET,RequestMethod.POST})
	public ResultBody deleteAll(String ids){
		return service.deleteAll(ids);
	}

}
