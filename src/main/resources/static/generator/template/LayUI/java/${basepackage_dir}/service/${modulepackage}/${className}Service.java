<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.${modulepackage};

import java.util.List;
import ${basepackage}.common.Page;

import ${basepackage}.model.vo.${modulepackage}.${className}Vo;
import ${basepackage}.model.domain.${modulepackage}.${className}Model;
import ${basepackage}.model.condition.${modulepackage}.${className}Condition;

/**
 * ${className}Service
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface ${className}Service {

    <#assign keyCloumn = ''>
    <#list table.columns as column>
    <#if column.pk>
    <#assign keyCloumn = column.columnNameLower>
    </#if>
    </#list>
    /**
     * 新增
     */
    int insert(${className}Model model) throws Exception;

    /**
     * 通过主键删除
     */
    int deleteById(int ${keyCloumn}) throws Exception;


    /**
     * 通过主键修改del_flag(删除标识),逻辑删除
     */
    int deleteLogic(int ${keyCloumn}) throws Exception;

    /**
     * 通过主键进行更新
     */
    int updateById(${className}Model model) throws Exception;

    /**
     * 通过主键获取对应对象
     */
    ${className}Model selectById(int ${keyCloumn});

    /**
     * 条件分页查询-未使用mybatis-plus
     */
    List<${className}Vo> findPageList(Page page, ${className}Condition condition);

    /**
     * 条件全部查询-用于导出
     */
    List<${className}Vo> findList(${className}Condition condition);
}