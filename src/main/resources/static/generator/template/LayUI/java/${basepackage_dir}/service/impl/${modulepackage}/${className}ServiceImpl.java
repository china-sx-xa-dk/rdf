<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl.${modulepackage};

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ${basepackage}.common.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.model.vo.${modulepackage}.${className}Vo;
import ${basepackage}.mapper.${modulepackage}.${className}Mapper;
import ${basepackage}.service.${modulepackage}.${className}Service;
import ${basepackage}.model.domain.${modulepackage}.${className}Model;
import ${basepackage}.model.condition.${modulepackage}.${className}Condition;

/**
 * ${className}ServiceImpl
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Service
@Transactional
@Slf4j
public class ${className}ServiceImpl implements ${className}Service {

    <#assign keyCloumn = ''>
    <#list table.columns as column>
    <#if column.pk>
    <#assign keyCloumn = column.columnNameLower>
    </#if>
    </#list>
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;

    @Override
    public int insert(${className}Model model) {
        return ${classNameLower}Mapper.insert(model);
    }

    @Override
    public int deleteById(int ${keyCloumn}) {
        return ${classNameLower}Mapper.deleteById(${keyCloumn});
    }

    @Override
    public int deleteLogic(int ${keyCloumn}) {
        UpdateWrapper<${className}Model> userModelUpdateWrapper = new UpdateWrapper<>();
        userModelUpdateWrapper.eq("${keyCloumn}", ${keyCloumn});
        userModelUpdateWrapper.set("del_flag", 1);
        return ${classNameLower}Mapper.update(new ${className}Model(), userModelUpdateWrapper);
    }

    @Override
    public int updateById(${className}Model model) {
        return ${classNameLower}Mapper.updateById(model);
    }

    @Override
    public ${className}Model selectById(int ${keyCloumn}) {
        return ${classNameLower}Mapper.selectById(${keyCloumn});
    }


    @Override
    public List<${className}Vo> findPageList(Page page, ${className}Condition condition) {
        return ${classNameLower}Mapper.findPageList(page, condition);
    }

    @Override
    public List<${className}Vo> findList(${className}Condition condition) {
        return ${classNameLower}Mapper.findList(condition);
    }
}