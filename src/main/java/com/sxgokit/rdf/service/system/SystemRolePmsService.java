package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.model.domain.system.SystemRolePms;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单权限service接口
 * @author DuKang
 */
public interface SystemRolePmsService{
	
	/**
	 * 查询单条角色菜单权限
	 * 
	 * @param rolePms：
	 * 		  roleId 权限ID 
	 *		  permissionId 菜单ID
	 * 
	 * @return Map
	 */
	Map selectByPrimaryKey(SystemRolePms rolePms);
	
	/**
	 * 删除单条角色菜单权限
	 * 
	 * @param rolePms： 
	 * 		  roleId 权限ID 
	 *		  permissionId 菜单ID 	
	 */
	void deleteByPrimaryKey(SystemRolePms rolePms);
	
	/**
	 * 添加角色菜单权限
	 * 
	 * @param permissionIds 角色菜单权限IDpermissionIds
	 * 		  roleId 角色ID	
	 */
	void insertSelective(String permissionIds, Integer roleId);
	
	/**
	 * 修改角色菜单权限
	 * 
	 * @param rolePms 角色菜单权限
	 */
	void updateSelective(SystemRolePms rolePms);
	
	/**
	 * 根据角色ID删除
	 * 
	 * @param roleId 角色ID
	 */
	void deleteByRoleId(Integer roleId);
	
	/**
	 * 根据菜单ID删除
	 * 
	 * @param permissionId 菜单ID
	 */
	void deleteByPermissionId(Integer permissionId);
	
	/**
	 * 根据角色ID查询角色已有权限
	 * 
	 * @param roleId
	 * @return 角色ID
	 */
	Map getPmsIdsByRoleId(Integer roleId);
	
	/**
	 * 查询已被分配菜单，删除菜单时以确定角色权限表是有需要删除数据
	 * @param permissionId
	 */
	List getPmsIdsByPermissionId(Integer permissionId);
	
	
}