<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package com.sxgokit.rdf.mapper.${modulepackage};

import java.util.List;
import ${basepackage}.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import ${basepackage}.model.vo.${modulepackage}.${className}Vo;
import ${basepackage}.model.domain.${modulepackage}.${className}Model;
import ${basepackage}.model.condition.${modulepackage}.${className}Condition;

/**
 * ${className}Mapper
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Repository
public interface ${className}Mapper extends BaseMapper<${className}Model> {

        List<${className}Vo> findPageList(@Param("page") Page page,@Param("condition") ${className}Condition condition);

        List<${className}Vo> findList(@Param("condition") ${className}Condition condition);
}