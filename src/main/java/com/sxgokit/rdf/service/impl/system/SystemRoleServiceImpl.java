package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SystemRoleDao;
import com.sxgokit.rdf.mapper.system.SystemRolePmsDao;
import com.sxgokit.rdf.model.domain.system.SystemRole;
import com.sxgokit.rdf.service.system.SystemRoleService;
import com.sxgokit.rdf.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色信息service
 *
 * @author wangpeng
 */
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    /**
     * 角色信息持续化接口
     */
    @Autowired
    private SystemRoleDao roleDao;

    /**
     * 角色菜单权限持续化接口
     */
    @Autowired
    private SystemRolePmsDao rolePmsDao;

    /**
     * 查询角色列表
     *
     * @param page 分页工具类
     *             role 查询条件对象
     * @return List
     */
    public List findPageList(Page page, SystemRole role) {
        return roleDao.findPageList(page, role);
    }

    /**
     * 根据主键查询角色信息
     *
     * @param roleId
     * @return Map
     */
    public Map selectByPrimaryKey(Integer roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }

    /**
     * 根据主键删除角色
     *
     * @param roleId
     */
    public void deleteByPrimaryKey(Integer roleId) {
        //删除角色
        roleDao.deleteByPrimaryKey(roleId);
        //删除角色同时删除角色菜单权限
        rolePmsDao.deleteByRoleId(roleId);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    public void insertSelective(SystemRole role) {
        roleDao.insertSelective(role);
    }

    /**
     * 修改角色
     *
     * @param role
     */
    public void updateSelective(SystemRole role) {
        roleDao.updateSelective(role);
    }

    /**
     * 查询启用中的角色
     */
    @Override
    public List findUsingList() {
        return roleDao.findUsingList();
    }

    @Override
    public SystemRole findById(Integer roleId) {
        return roleDao.findById(roleId);
    }
}
