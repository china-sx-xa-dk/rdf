package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.model.domain.system.SystemPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单service接口
 *
 * @author wangpeng
 */
public interface SystemPermissionService{

    /**
     * 获取菜单列表
     *
     * @return
     */
    List getPermissionList();

    /**
     * 根据主键查询
     *
     * @param permissionId
     * @return Map
     */
    Map selectByPrimaryKey(@Param("permissionId") Integer permissionId);

    /**
     * 根据主键删除
     *
     * @param permissionId
     */
    void deleteByPrimaryKey(@Param("permissionId") Integer permissionId);

    /**
     * 添加菜单
     *
     * @param permission 菜单信息
     */
    void insertSelective(SystemPermission permission);

    /**
     * 修改菜单
     *
     * @param permission 菜单信息
     */
    void updateSelective(SystemPermission permission);

    /**
     * 查询登录用户角色权限
     *
     * @param adminId
     */
    List getUserPmsList(Integer adminId);

    List findList(SystemPermission systemPermission);

    SystemPermission findById(Integer permissionId);
}