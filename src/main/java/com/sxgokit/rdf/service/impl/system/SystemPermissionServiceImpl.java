package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SystemPermissionDao;
import com.sxgokit.rdf.mapper.system.SystemRolePmsDao;
import com.sxgokit.rdf.model.domain.system.SystemPermission;
import com.sxgokit.rdf.service.system.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单service接口实现
 *
 * @author wangpeng
 */
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

    /**
     * 系统菜单持续化接口
     */
    @Autowired
    private SystemPermissionDao permissionDao;

    /**
     * 角色菜单权限持续化接口
     */
    @Autowired
    private SystemRolePmsDao rolePmsDao;

    /**
     * 获取菜单列表
     */
    @Override
    public List getPermissionList() {
        return permissionDao.getPermissionList();
    }

    /**
     * 根据主键查询
     */
    @Override
    public Map selectByPrimaryKey(Integer permissionId) {
        return permissionDao.selectByPrimaryKey(permissionId);
    }

    /**
     * 根据主键删除
     */
    @Override
    public void deleteByPrimaryKey(Integer permissionId) {
        // 删除菜单
        permissionDao.deleteByPrimaryKey(permissionId);
        // 同时删除角色菜单权限
        rolePmsDao.deleteByPermissionId(permissionId);

    }

    /**
     * 添加菜单
     */
    @Override
    public void insertSelective(SystemPermission permission) {
        // 设置菜单类型为链接
        permission.setPermissionType(1);
        permissionDao.insertSelective(permission);
    }

    /**
     * 修改菜单
     */
    @Override
    public void updateSelective(SystemPermission permission) {
        permissionDao.updateSelective(permission);
    }

    /**
     * 查询登录用户角色权限
     */
    @Override
    public List getUserPmsList(Integer adminId) {
        return permissionDao.getUserPmsList(adminId);
    }

    @Override
    public List findList(SystemPermission systemPermission) {
        return permissionDao.findList(systemPermission);
    }

    @Override
    public SystemPermission findById(Integer permissionId) {
        return permissionDao.findById(permissionId);
    }
}
