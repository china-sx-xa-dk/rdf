package com.sxgokit.rdf.model.domain.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单Model
 * 
 * @author wangpeng
 */
@Data
public class SystemPermission implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	private Integer permissionId;

	/**
	 * 菜单名称
	 */
	private String permissionName;

	/**
	 * 父级菜单名称
	 */
	private Integer permissionParentId;

	private SystemPermission parent; // 父级菜单

	/**
	 * 菜单类型 1：链接  2：按钮
	 */
	private Integer permissionType;

	/**
	 * 菜单路径
	 */
	private String permissionValue;

	/**
	 * 菜单排序
	 */
	private Integer permissionSort;

	/**
	 * 是否在菜单中显示(1是，0否)
	 */
	private Integer isShow;

	/**
	 * 父级id串
	 */
	private String parentIds;

	private Integer userId;

	public SystemPermission()
	{
		super();
		this.isShow = 1;
	}

	@JsonIgnore
	public static void sortList(List<SystemPermission> list, List<SystemPermission> sourcelist,
								Integer parentId, boolean cascade)
	{
		for (int i = 0; i < sourcelist.size(); i++ )
		{
			SystemPermission e = sourcelist.get(i);
			if (e.getPermissionParentId() != null
					&& e.getPermissionParentId().equals(parentId))
			{
				list.add(e);
				if (cascade)
				{
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j = 0; j < sourcelist.size(); j++ )
					{
						SystemPermission child = sourcelist.get(j);
						if (child.getPermissionParentId() != null
								&& child.getPermissionParentId().equals(e.getPermissionId()))
						{
							sortList(list, sourcelist, e.getPermissionId(), true);
							break;
						}
					}
				}
			}
		}
	}
}