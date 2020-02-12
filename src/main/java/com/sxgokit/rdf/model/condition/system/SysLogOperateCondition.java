package com.sxgokit.rdf.model.condition.system;

import com.sxgokit.rdf.model.domain.system.SysLogOperateModel;
import lombok.Data;

/**
 *  sys_log_operate
 * @author liwei 2019-06-11
 * @Description: 页面和前端获取到的SysLogOperateModel的对象,包含页面传输字段
 */
@Data
public class SysLogOperateCondition extends SysLogOperateModel {

    /**
     * 每页显示的记录数
     */
    private Integer showCount;

    /**
     * 当前页
     */
    private Integer currentPage;
}
