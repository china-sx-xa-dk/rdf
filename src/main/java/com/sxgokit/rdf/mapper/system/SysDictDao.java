package com.sxgokit.rdf.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据字典持续化接口
 * @author liwei
 */

@Repository
public interface SysDictDao extends BaseMapper<SysDictDao> {

    /**
     * 分页查询 数据字典列表
     * @param  page 分页 sysDict 数据字典
     */
    List findPageList(@Param("page") Page page, @Param("sysDict") SysDict sysDict);


    /**
     * 物理删除数据字典
     * @param id
     */
    void deleteByDictId(Integer id);

    /**
     *根据主键查询数据字典
     * @param id
     * @return SysDict
     */
    SysDict selectById(Integer id);

    /**
     * 查询数据字典的数据类型（去重）
     */
    List<String> selectType();

    /**
     * 修改数据字典
     * @param sysDict
     */
    void updateSysDict(@Param("sysDict") SysDict sysDict);


    /**
     * 新增数据字典
     * @Param sysDict
     */
    void insertSysDict(@Param("sysDict") SysDict sysDict);

    List<SysDict> findAllList(@Param("sysDict") SysDict sysDict);
}