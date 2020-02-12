package com.sxgokit.rdf.service.system;

import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysLogOperateCondition;
import com.sxgokit.rdf.model.domain.system.SysLogOperateModel;
import com.sxgokit.rdf.model.vo.system.SysLogOperateVo;

import java.util.List;

/**
 * @Author: liwei
 * @Date: 2019/6/11/
 * @Description: 系统日志操作接口
 */

public interface SysLogOperateService {

    /**
     * 条件分页查询-未使用mybatis-plus
     */
    List<SysLogOperateVo> findPageList(Page page, SysLogOperateCondition condition);

    /**
     * 查询系统日志（根据logId日志id）
     */
    SysLogOperateVo selectByLogId(String logId);

    /**
     * 修改系统日志
     */
    int updateById(SysLogOperateModel sysLogOperateModel);

    /**
     * 新增系统日志
     */
    int insertById(SysLogOperateModel sysLogOperateModel);


}
