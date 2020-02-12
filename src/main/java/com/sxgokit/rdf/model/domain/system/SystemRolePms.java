package com.sxgokit.rdf.model.domain.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限关联Model
 * 
 * @author wangpeng
 * 
 */
@Data
public class SystemRolePms implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private Integer permissionId;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 子菜单
	 */
	private String childIds;
	/**
	 * 添加人
	 */
	private Integer rolePerCreateUser;
	/**
	 * 添加时间
	 */
	private Date rolePerCreateDate;
}