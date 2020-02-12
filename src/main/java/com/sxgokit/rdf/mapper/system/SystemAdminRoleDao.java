package com.sxgokit.rdf.mapper.system;

import com.sxgokit.rdf.model.domain.system.SystemAdminRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 15:55
 */
@Repository
public interface SystemAdminRoleDao{

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