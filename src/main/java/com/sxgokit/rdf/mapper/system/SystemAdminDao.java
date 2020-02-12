package com.sxgokit.rdf.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户信息持续化接口
 *
 * @author wangpeng
 */
@Repository
public interface SystemAdminDao extends BaseMapper<SystemAdmin> {
    /**
     * 分页查询用户列表
     *
     * @Param page 分页 admin 用户信息
     */
    List findPageList(@Param("page") Page page, @Param("admin") SystemAdmin admin);
    /**
     * 根据主键查询用户信息
     *
     * @param adminId
     * @return
     */
    Map selectByPrimaryKey(@Param("adminId") Integer adminId);


    /**
     * 根据主键删除用户信息(真删)
     *
     * @param adminId
     */
    void deleteByPrimaryKey(@Param("adminId") Integer adminId);

    /**
     * 添加用户
     *
     * @param admin
     */
    void insertSelective(SystemAdmin admin);

    /**
     * 修改用户
     *
     * @param admin
     */
    void updateSelective(SystemAdmin admin);

    /**
     * 用户登录
     *
     * @param admin
     * @return SystemAdmin
     */
    SystemAdmin login(SystemAdmin admin);

    SystemAdmin checkLoginName(@Param("loginName") String loginName, @Param("user_id") Integer userId);

    SystemAdmin getInfoById(@Param("id") Integer id);

    List findList(SystemAdmin admin);
}