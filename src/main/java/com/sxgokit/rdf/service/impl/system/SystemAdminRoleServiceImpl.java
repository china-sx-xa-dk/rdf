package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.mapper.system.SystemAdminRoleDao;
import com.sxgokit.rdf.model.domain.system.SystemAdminRole;
import com.sxgokit.rdf.service.system.SystemAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户权限service接口实现
 *
 * @author wangpeng
 */
@Service
public class SystemAdminRoleServiceImpl implements SystemAdminRoleService {

    /**
     * 用户信息持续化接口
     */
    @Autowired
    private SystemAdminRoleDao adminRoleDao;

    @Override
    public void deleteByAdminId(Integer adminId) {
        adminRoleDao.deleteByAdminId(adminId);
    }

    @Override
    public void insert(SystemAdminRole adminRole) {
        adminRoleDao.insert(adminRole);
    }

    @Override
    public void update(SystemAdminRole adminRole) {
        adminRoleDao.update(adminRole);
    }
}
