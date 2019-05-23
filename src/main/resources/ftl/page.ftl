package ${modelPackage}.Page;

import lombok.Data;


/** 
*@TitlePage.java
*@description:  Page
*@author lhc
**/
@Data
public class Page{
	
	private Integer page; //页码
    private Integer limit; //条数
	
}
