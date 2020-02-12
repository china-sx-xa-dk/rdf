<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model.condition.${modulepackage};

import lombok.Data;
import java.io.Serializable;

import ${basepackage}.model.domain.${modulepackage}.${className}Model;

/**
 * ${className}Condition
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
public class ${className}Condition extends ${className}Model implements Serializable {

    private static final long serialVersionUID = ${className}Condition.class.getName().hashCode();

    /**
     * 每页显示的记录数
     */
    private Integer showCount;

    /**
     * 当前页
     */
    private Integer currentPage;

    <#list table.columns as column>
    <#if column.columnNameLower == 'createTime'>
    private String createTimeStr;
    <#elseif column.columnNameLower == 'updateTime'>

    private String updateTimeStr;
    <#else>
    </#if>
    </#list>

    <#if dataAuthorityType == '3'>
    <#list table.columns as column>
    <#if (column.columnNameLower == 'orgId')>
    /**
     * 数据所属组织机构ID数组
     */
    private Integer[] orgIdArray;
    </#if>
    </#list>
    </#if>
}