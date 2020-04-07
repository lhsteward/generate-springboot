package ${modelPackage}.${tableName};


<#list typeList as list>
<#if list == "Timestamp">
import java.sql.Timestamp;
</#if>
</#list>
import lombok.Data;


/** 
*@Title ${tableName}.java 
*@description:  ${tableName}
*@author lhc
**/
@Data
@ApiModel(description = "${tableName}")
public class ${tableName} extends Page{
	
	${property}
	
}
