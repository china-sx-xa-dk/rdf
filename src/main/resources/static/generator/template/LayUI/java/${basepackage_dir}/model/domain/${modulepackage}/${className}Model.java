<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model.domain.${modulepackage};

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ${className}Model
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
@TableName("${table.sqlName}")
public class ${className}Model extends Model<${className}Model> implements Serializable {

    private static final long serialVersionUID = ${className}Model.class.getName().hashCode();

    <@generateFieldsNew/>

    <#macro generateFieldsNew>
    <#--自定义函数，根据数据库中表字段生成java中的属性 -->
    <#list table.columns as column>
    /** ${column.columnAlias!} */
    <#if column.isDateTimeColumn>
    <#if (column.columnNameLower != 'createTime')&&(column.columnNameLower != 'updateTime')>
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    <#elseif (column.columnNameLower == 'createTime')>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    <#elseif (column.columnNameLower == 'updateTime')>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    <#else>
    </#if>
    @Excel(name = "${column.columnAlias!}", orderNum = "${column_index}",width=20.0)
    private ${column.javaType} ${column.columnNameLower};

    <#else>
    <#if column.pk>
    @TableId(type = IdType.AUTO)
    </#if>
    @Excel(name = "${column.columnAlias!}", orderNum = "${column_index}",width=20.0)
    private ${column.javaType} ${column.columnNameLower};

    </#if>
    </#list>
    </#macro>
}