package com.sxgokit.rdf.model.domain.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 15:55
 */
@Data
public class SystemAdminRole implements Serializable {
    
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Integer adminId;
    
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色对应名称
     */
    private String roleName;

}