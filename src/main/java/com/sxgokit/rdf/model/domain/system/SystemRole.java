package com.sxgokit.rdf.model.domain.system;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息Model
 * 
 * @author wangpeng
 */
@Data
public class SystemRole implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色编号
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色状态
	 */
	private Integer roleState;
	/**
	 * 角色描述
	 */
	private String roleDesc;
	/**
	 * 创建人
	 */
	private Integer roleCreateUser;
	/**
	 * 创建时间
	 */
	private Date roleCreateDate;
	/**
	 * 修改人
	 */
	private Integer roleUpdateUser;
	/**
	 * 修改时间
	 */
	private Date roleUpdateDate;
}