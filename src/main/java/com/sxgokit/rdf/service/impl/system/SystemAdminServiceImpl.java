package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SystemAdminDao;
import com.sxgokit.rdf.mapper.system.SystemAdminRoleDao;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.model.domain.system.SystemAdminRole;
import com.sxgokit.rdf.service.system.SystemAdminService;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 用戶信息service接口实现
 * @author DuKang
 */
@Service
public class SystemAdminServiceImpl implements SystemAdminService {

    /**
     * 用户信息持续化接口
     */
    @Autowired
    private SystemAdminDao adminDao;

    /**
     * 用户角色持续化接口
     */
    @Autowired
    private SystemAdminRoleDao adminRoleDao;

    /**
     * 分页查询用户列表
     */
    @Override
    public List findPageList(Page page, SystemAdmin admin) {
        return adminDao.findPageList(page, admin);
    }

    /**
     * 根据主键查询用户信息
     *
     * @param adminId
     * @return
     */
    @Override
    public Map selectByPrimaryKey(Integer adminId) {
        return adminDao.selectByPrimaryKey(adminId);
    }

    /**
     * 根据主键删除用户信息(删除数据库数据)
     *
     * @param adminId
     */
    @Override
    public void deleteByPrimaryKey(Integer adminId) {
        //删除用户信息
        adminDao.deleteByPrimaryKey(adminId);
        //删除对应用户角色分配信息
        adminRoleDao.deleteByAdminId(adminId);
    }

    /**
     * 添加用户
     *
     * @param admin
     */
    public void insertSelective(SystemAdmin admin) {
        // 新增时保存数据
        admin.setLoginPass(DataPool.LOGIN_PASS);
        admin.setAdminCreateDate(new Date());
        adminDao.insertSelective(admin);

        if(null != admin.getRoleId())
        {
            //用户角色信息
            SystemAdminRole adminRole = new SystemAdminRole();
            adminRole.setRoleId(admin.getRoleId());
            adminRole.setAdminId(admin.getAdminId());//添加后返回的adminID
            //添加用户角色
            adminRoleDao.insert(adminRole);
        }
    }

    @Override
    public SystemAdmin checkLoginName(String loginName, Integer userId) {
        SystemAdmin systemAdmin = adminDao.checkLoginName(loginName, userId);
        return systemAdmin;
    }

    /**
     * 修改用户信息及角色
     *
     * @param admin
     */
    public void updateAdminAndRole(SystemAdmin admin) {
        //修改用户信息
        adminDao.updateSelective(admin);
        //修改用户角色信息
        SystemAdminRole adminRole = new SystemAdminRole();
        adminRole.setRoleId(admin.getRoleId());
        adminRole.setAdminId(admin.getAdminId());
        //更新用户角色
        adminRoleDao.deleteByAdminId(admin.getAdminId());
        adminRoleDao.insert(adminRole);
    }

    /**
     * 用户登录
     */
    @Override
    public SystemAdmin login(SystemAdmin admin) {
        return adminDao.login(admin);
    }

    /**
     * 修改密码
     *
     * @param admin
     */
    public void updateSelective(SystemAdmin admin) {
        adminDao.updateSelective(admin);
    }

    @Override
    public SystemAdmin getInfoById(Integer id) {
        SystemAdmin systemAdmin = adminDao.getInfoById(id);
        if (null != systemAdmin) {
            systemAdmin.setLoginPass("");
            return systemAdmin;
        }
        return null;
    }

    @Override
    public List findList(SystemAdmin admin) {
        return adminDao.findList(admin);
    }
}
