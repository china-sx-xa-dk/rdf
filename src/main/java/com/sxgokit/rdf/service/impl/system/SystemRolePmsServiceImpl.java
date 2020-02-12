package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SystemPermissionDao;
import com.sxgokit.rdf.mapper.system.SystemRolePmsDao;
import com.sxgokit.rdf.model.domain.system.SystemRolePms;
import com.sxgokit.rdf.service.system.SystemRolePmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色菜单权限service接口
 * @author DuKang
 */
@Service
public class SystemRolePmsServiceImpl implements SystemRolePmsService {

    /**
     * 角色菜单权限持续化接口
     */
    @Autowired
    private SystemRolePmsDao rolePmsDao;

    /**
     * 系统菜单持续化接口
     */
    @Autowired
    private SystemPermissionDao permissionDao;

    /**
     * 查询单条角色菜单权限
     */
    @Override
    public Map selectByPrimaryKey(SystemRolePms rolePms) {
        return rolePmsDao.selectByPrimaryKey(rolePms);
    }

    /**
     * 删除单条角色菜单权限
     */
    @Override
    public void deleteByPrimaryKey(SystemRolePms rolePms) {
        rolePmsDao.deleteByPrimaryKey(rolePms);
    }


    /**
     * 添加角色菜单权限
     *
     * @param roleId 角色菜单权限
     *               permissionIds  菜单ID
     */
    @Override
    public void insertSelective(String permissionIds, Integer roleId) {
        //重新授权时先删除历史授权记录
        rolePmsDao.deleteByRoleId(roleId);

        String[] idArr = permissionIds.split(",");
        List<SystemRolePms> rolePmsList = new ArrayList<SystemRolePms>();
        for (int i = 0; i < idArr.length; i++) {
            SystemRolePms rolePms = new SystemRolePms();
            //permissionId等于1时为树的最顶层节点
            if (idArr[i].equals("1")) {
                continue;
            }
            rolePms.setRoleId(roleId);
            rolePms.setPermissionId(Integer.parseInt(idArr[i]));
            rolePmsList.add(rolePms);
        }
        rolePmsDao.insertSelective(rolePmsList);
    }


    /**
     * 修改角色菜单权限
     *
     * @param rolePms 角色菜单权限
     */
    @Override
    public void updateSelective(SystemRolePms rolePms) {
        rolePmsDao.updateSelective(rolePms);
    }

    /**
     * 根据角色ID删除
     */
    @Override
    public void deleteByRoleId(Integer roleId) {
        rolePmsDao.deleteByRoleId(roleId);
    }

    /**
     * 根据菜单ID删除
     */
    @Override
    public void deleteByPermissionId(Integer PermissionId) {
        rolePmsDao.deleteByPermissionId(PermissionId);
    }

    /**
     * 查询已有权限
     */
    @Override
    public Map getPmsIdsByRoleId(Integer roleId) {
        Map map = new HashMap();
        List permissionList = permissionDao.getPermissionList();
        List relationList = rolePmsDao.getPmsIdsByRoleId(roleId);
        map.put("permissionList", permissionList);
        map.put("relationList", relationList);
        return map;
    }

    /**
     * 查询已被分配权限菜单
     */
    @Override
    public List getPmsIdsByPermissionId(Integer permissionId) {
        return rolePmsDao.getPmsIdsByPermissionId(permissionId);
    }
}