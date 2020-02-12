package com.sxgokit.rdf.model.condition.app;

import com.sxgokit.rdf.model.domain.app.AppUserModel;
import lombok.Data;

/**
 * @Author: Dukang
 * @Date: 2019/5/29/0029 18:54
 * @Description: 页面和前端获取到的appUser的对象,包含页面传输字段
 */
@Data
public class AppUserCondition extends AppUserModel {

    /**
     * 每页显示的记录数
     */
    private Integer showCount;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 组织机构id（仅查询使用）
     */
    private Integer orgId;

    /**
     * 组织机构id（仅查询使用,排除该机构下用户）
     */
    private Integer orgIdNot;

    /**
     * 选中用户id串
     */
    private String ids;
}
