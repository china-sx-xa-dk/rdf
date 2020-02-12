package com.sxgokit.rdf.service.impl.system;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxgokit.rdf.mapper.system.SysOrganizationDao;
import com.sxgokit.rdf.model.domain.system.SysOrganization;
import com.sxgokit.rdf.model.domain.system.SystemOrgTree;
import com.sxgokit.rdf.service.system.SysOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 16:50
 */
@Service
@Transactional
@Slf4j
public class SysOrganizationServiceImpl implements SysOrganizationService {


    @Autowired
    private SysOrganizationDao sysOrganizationDao;

    /**
     * 根据主键删除
     */
    @Override
    public void deleteByPrimaryKey(Integer orgId) {
        // 删除机构
        sysOrganizationDao.deleteByPrimaryKey(orgId);
    }

    /**
     * 添加机构
     */
    @Override
    public void insertSelective(SysOrganization sysOrganization) {
        sysOrganizationDao.insertSelective(sysOrganization);
    }

    /**
     * 修改机构
     */
    @Override
    public void updateSelective(SysOrganization sysOrganization) {
        sysOrganizationDao.updateSelective(sysOrganization);
    }

    @Override
    public List<SysOrganization> findList(Integer orgId) {
        return sysOrganizationDao.findList(orgId);
    }

    @Override
    public SysOrganization findById(Integer orgId) {
        return sysOrganizationDao.findById(orgId);
    }

    @Override
    public String getOrgTreeData(Integer orgId){
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SystemOrgTree> list = sysOrganizationDao.getOrgTreeData(orgId);
        for (int i = 0; i < list.size(); i++ )
        {
            SystemOrgTree tree = list.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", tree.getId());
            map.put("pId", tree.getParentId());
            map.put("name", tree.getName());
            mapList.add(map);
        }
        String treeStr = JSONArray.toJSONString(mapList);
        return treeStr;
    }

    @Override
    public Integer[] getAllOrgIdByOrg(Integer orgId) {
        List<Integer> orgIdList = sysOrganizationDao.getAllOrgIdByOrg(orgId);
        Integer[] orgIdArray = orgIdList.toArray(new Integer[orgIdList.size()]);
        return orgIdArray;
    }
}
