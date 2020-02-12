package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.common.Page;

import java.util.List;

/**
 * 数据字典service接口
 * @author liwei
 */

public interface SysDictService {
    /**
     * 查询数据字典列表
     * @param page 分页工具类  sysDict 数据字典查询条件对象
     * @return List
     */
    List findPageList(Page page, SysDict sysDict);

    /**
     * 查询全部字典列表
     */
    List<SysDict> findAll();

    /**
     * 根据主键id删除数据字典
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据主键id查询数据字典
     * @param id
     */
    SysDict selectById(Integer id);

    /**
     * 查询数据字典的数据类型
     */
     List selectType();

    /**
     * 新增数据字典
     * @Param sysDict
     */
    void insertSysDict(SysDict sysDict);

    /**
     * 编辑数据字典
     * @param sysDict
     */
    void updateSysDict(SysDict sysDict);


}