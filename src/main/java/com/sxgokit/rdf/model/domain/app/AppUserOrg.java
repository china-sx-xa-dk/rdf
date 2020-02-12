package com.sxgokit.rdf.model.domain.app;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhao
 * @date 2019/6/5 11:28
 */
@Data
public class AppUserOrg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 机构编号
     */
    private Integer orgId;

    public AppUserOrg() {
    }
}
