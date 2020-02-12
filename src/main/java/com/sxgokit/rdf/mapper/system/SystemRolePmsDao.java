package com.sxgokit.rdf.mapper.system;

import com.sxgokit.rdf.model.domain.system.SystemRolePms;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单权限持续化接口
 *
 * @author wangpeng
 */
@Repository
public interface SystemRolePmsDao {

    /**
     * 查询单条角色菜单权限
     *
     * @param rolePms： roleId 权限ID
     *                 permissionId 菜单ID
     * @return Map
     */
    Map selectByPrimaryKey(SystemRolePms rolePms);

    /**
     * 删除单条角色菜单权限
     *
     * @param rolePms： roleId 权限ID
     *                 permissionId 菜单ID
     */
    void deleteByPrimaryKey(SystemRolePms rolePms);


    /**
     * 添加角色菜单权限
     *
     * @param list 角色菜单权限
     */
    void insertSelective(List list);


    /**
     * 修改角色菜单权限
     *
     * @param rolePms 角色菜单权限
     */
    void updateSelective(SystemRolePms rolePms);

    /**
     * 根据角色ID删除
     *
     * @param roleId 角色ID
     */
    void deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据菜单ID删除
     *
     * @param permissionId 菜单ID
     */
    void deleteByPermissionId(@Param("permissionId") Integer permissionId);

    /**
     * 根据角色ID查询角色已有权限
     *
     * @param roleId
     * @return 角色ID
     */
    List getPmsIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据菜单ID查询权限信息
     *
     * @param permissionId
     */
    List getPmsIdsByPermissionId(@Param("permissionId") Integer permissionId);
}