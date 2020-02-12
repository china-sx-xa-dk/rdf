package com.sxgokit.rdf.mapper.system;

import com.sxgokit.rdf.model.domain.system.SystemRole;
import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统权限持续化接口
 *
 * @author wangpeng
 */
@Repository
public interface SystemRoleDao {

    /**
     * 分页查询角色列表
     *
     * @Param page 分页 role 角色查询对象
     */
    List findPageList(@Param("page") Page page, @Param("role") SystemRole role);

    /**
     * 根据主键查询角色信息
     *
     * @param roleId
     * @return
     */
    Map selectByPrimaryKey(@Param("roleId") Integer roleId);

    /**
     * 根据主键删除角色信息
     *
     * @param roleId
     */
    void deleteByPrimaryKey(@Param("roleId") Integer roleId);

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