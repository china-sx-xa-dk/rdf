package com.sxgokit.rdf.service.system;


import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.common.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 用户信息service接口
 * @author DuKang
 */
public interface SystemAdminService{

    /**
     * 查询会员列表
     *
     * @param page 分页工具类 admin 查询条件对象
     * @return List
     */
    List findPageList(Page page, SystemAdmin admin);

    /**
     * 根据主键查询用户信息
     *
     * @param adminId
     * @return Map
     */
    Map selectByPrimaryKey(Integer adminId);

    /**
     * 根据主键删除用户信息(真删除)
     *
     * @param adminId
     */
    void deleteByPrimaryKey(Integer adminId);

    /**
     * 添加用户
     *
     * @param admin
     */
    void insertSelective(SystemAdmin admin);

    /**
     * 修改用户信息及角色
     *
     * @param admin
     */
    void updateAdminAndRole(SystemAdmin admin);

    /**
     * 用户登录
     *
     * @return admin
     */
    SystemAdmin login(SystemAdmin admin);

    /**
     * 修改用户
     *
     * @return admin
     */
    void updateSelective(SystemAdmin admin);


    SystemAdmin checkLoginName(String loginName, Integer userId);

    SystemAdmin getInfoById(Integer id);

    List findList(SystemAdmin admin);
}