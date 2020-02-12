package com.sxgokit.rdf.mapper.system;

import com.sxgokit.rdf.model.domain.system.SystemPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单持续化接口
 *
 * @author wangpeng
 */
@Repository
public interface SystemPermissionDao {

    /**
     * 获取菜单列表
     *
     * @return List
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
     */
    List getUserPmsList(@Param("adminId") Integer adminId);

    List findList(SystemPermission systemPermission);

    SystemPermission findById(Integer permissionId);
}