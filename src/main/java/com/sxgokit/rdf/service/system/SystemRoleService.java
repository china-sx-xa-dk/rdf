package com.sxgokit.rdf.service.system;


import com.sxgokit.rdf.model.domain.system.SystemRole;
import com.sxgokit.rdf.common.Page;

import java.util.List;
import java.util.Map;

/**
 * 角色service接口
 * @author DuKang
 */
public interface SystemRoleService{

    /**
     * 查询角色列表
     *
     * @param page 分页工具类 role 查询条件对象
     * @return List
     */
    List findPageList(Page page, SystemRole role);

    /**
     * 根据主键查询角色信息
     *
     * @param roleId
     * @return Map
     */
    Map selectByPrimaryKey(Integer roleId);

    /**
     * 根据主键删除角色
     *
     * @param roleId
     */
    void deleteByPrimaryKey(Integer roleId);

    /**
     * 添加角色
     *
     * @param role
     */
    void insertSelective(SystemRole role);

    /**
     * 修改角色
     *
     * @param role
     */
    void updateSelective(SystemRole role);

    /**
     * 查询启用中角色
     */
    List findUsingList();

    SystemRole findById(Integer roleId);
}