package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.model.domain.system.SysOrganization;
import com.sxgokit.rdf.model.domain.system.SystemOrgTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 16:50
 */
public interface SysOrganizationService{


    /**
     * 根据主键删除
     *
     * @param orgId
     */
    void deleteByPrimaryKey(@Param("orgId") Integer orgId);

    /**
     * 添加机构
     *
     * @param sysOrganization 机构信息
     */
    void insertSelective(SysOrganization sysOrganization);

    /**
     * 修改机构
     *
     * @param sysOrganization 机构信息
     */
    void updateSelective(SysOrganization sysOrganization);

    /**
     * 获取组织机构列表数据
     * @param orgId 当前管理员的所属组织ID，用于数据权限
     * @return
     */
    List findList(Integer orgId);

    SysOrganization findById(Integer orgId);

    /**
     * 获取组织机构tree数据
     * @param orgId 要获取的机构ID(为null则获取所有组织)
     * @author wgl
     * @return
     */
    String getOrgTreeData(Integer orgId);

    /**
     * 根据传入组织机构ID获取其组织下对应的所有本级及下级ID集合
     * @param orgId
     * @return
     */
    Integer[] getAllOrgIdByOrg(Integer orgId);
}
