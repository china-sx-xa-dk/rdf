package com.sxgokit.rdf.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.model.domain.system.SysOrganization;
import com.sxgokit.rdf.model.domain.system.SystemOrgTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 15:53
 */
@Repository
public interface SysOrganizationDao extends BaseMapper<SysOrganization> {

    List<SysOrganization> findList(@Param("orgId") Integer orgId);

    SysOrganization findById(Integer orgId);

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
     * 获取组织机构tree数据
     * @param orgId 要获取的机构ID(为null则获取所有组织)
     * @author wgl
     * @return
     */
    List<SystemOrgTree> getOrgTreeData(@Param("orgId") Integer orgId);

    /**
     * 根据传入组织机构ID获取其组织下对应的所有本级及下级ID集合
     * @param orgId
     * @return
     */
    List<Integer> getAllOrgIdByOrg(@Param("orgId") Integer orgId);
}
