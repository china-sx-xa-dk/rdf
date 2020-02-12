package com.sxgokit.rdf.model.condition.system;

import com.sxgokit.rdf.model.domain.system.SysConfigModel;

/**
 * @Author: liwei
 * @Date: 2019/6/5/0029 18:54
 * @Description: 页面和前端获取到的SysConfig的对象,包含页面传输字段
 */

public class SysConfigCondition extends SysConfigModel {

    /**
     * 每页显示的记录数
     */
    private Integer showCount;

    /**
     * 当前页
     */
    private Integer currentPage;
}
