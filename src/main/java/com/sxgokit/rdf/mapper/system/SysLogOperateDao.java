package com.sxgokit.rdf.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysLogOperateCondition;
import com.sxgokit.rdf.model.domain.system.SysLogOperateModel;
import com.sxgokit.rdf.model.vo.system.SysLogOperateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author liwei 2019-06-11
 * @Description:
 */

@Repository
public interface SysLogOperateDao extends BaseMapper<SysLogOperateModel> {

    List<SysLogOperateVo> findPageList(@Param("page") Page page,
                                       @Param("condition") SysLogOperateCondition condition);


    SysLogOperateVo selectByLogId(@Param("logId") String logId);

    int insertSysLogOperate(@Param("sysLogOperateModel") SysLogOperateModel sysLogOperateModel);

    int updateSysLogOperate(@Param("sysLogOperateModel") SysLogOperateModel sysLogOperateModel);

}
