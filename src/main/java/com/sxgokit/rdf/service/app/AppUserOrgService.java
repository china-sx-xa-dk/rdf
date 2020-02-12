package com.sxgokit.rdf.service.app;

import com.sxgokit.rdf.model.domain.app.AppUserOrg;

/**
 * @Author: wuhao
 * @Date: 2019年6月5日14:14:16
 * @Description: app登录用户组织机构操作接口
 */
public interface AppUserOrgService {

    /**
     * 新增
     */
    int insert(AppUserOrg appUserOrg);

    /**
     * 通过id删除
     */
    int delete(AppUserOrg appUserOrg);

}
