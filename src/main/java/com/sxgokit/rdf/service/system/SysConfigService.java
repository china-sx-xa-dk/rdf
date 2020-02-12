package com.sxgokit.rdf.service.system;


import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysConfigCondition;
import com.sxgokit.rdf.model.domain.system.SysConfigModel;
import com.sxgokit.rdf.model.vo.system.SysConfigVo;

import java.util.List;

/**
 * @Author: liwei
 * @Date: 2019/6/6
 * @Description: 系统配置操作接口
 */

public interface SysConfigService {

    /**
     * 新增
     */
    int insertSysConfig(SysConfigModel sysConfigModel);


    /**
     * 条件分页查询-未使用mybatis-plus
     */
    List<SysConfigVo> findPageList(Page page, SysConfigCondition sysConfigCondition);

    /**
     * 通过configId删除(物理删除)
     */
    int deleteByConfigId(String configId);

    /**
     * 查询（根据configId）
     */
    SysConfigModel selectById(String configId);

    /**
     * 编辑（根据configId）
     */
    int updateSysConfig(SysConfigModel sysConfigModel);



}
