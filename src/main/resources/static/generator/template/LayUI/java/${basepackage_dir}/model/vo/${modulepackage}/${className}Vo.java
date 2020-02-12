<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model.vo.${modulepackage};

import lombok.Data;
import java.io.Serializable;

import ${basepackage}.model.domain.${modulepackage}.${className}Model;

/**
 * ${className}Vo
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
public class ${className}Vo extends ${className}Model implements Serializable {

    private static final long serialVersionUID = ${className}Vo.class.getName().hashCode();

    <#list table.columns as column>
    <#if column.columnNameLower == 'createUser'>
    private String createUserName;
    <#elseif column.columnNameLower == 'updateUser'>
    private String updateUserName;
    <#else>
    </#if>
    </#list>

}