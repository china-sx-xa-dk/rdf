package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.model.domain.system.SystemAdminRole;
import org.apache.ibatis.annotations.Param;


/**
 * 用户角色service接口
 * 
 * @author wangpeng
 */
public interface SystemAdminRoleService{

	/**
	 * 根据用户ID删除用户角色
	 * 
	 * @param adminId
	 */
	void deleteByAdminId(@Param("adminId") Integer adminId);

	/**
	 * 添加用户角色
	 * 
	 * @param adminRole
	 */
	void insert(SystemAdminRole adminRole);

	/**
	 * 修改用户角色
	 * 
	 * @param adminRole
	 */
	void update(SystemAdminRole adminRole);
}