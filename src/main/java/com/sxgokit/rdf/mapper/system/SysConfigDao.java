package com.sxgokit.rdf.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysConfigCondition;
import com.sxgokit.rdf.model.domain.system.SysConfigModel;
import com.sxgokit.rdf.model.vo.system.SysConfigVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liwei
 * @date 2019/06/05
 */
@Repository
public interface SysConfigDao extends BaseMapper<SysConfigModel> {

    /**
     * 查询系统配置（分页查询）
     * @param page
     * @param condition
     * @return
     */
    List<SysConfigVo> findPageList(@Param("page") Page page, @Param("condition") SysConfigCondition condition );

    /**
     * 新增系统配置
     */

    int insertSysConfig(@Param("sysConfigModel") SysConfigModel sysConfigModel);

    /**
     * 查询系统配置（根据configId）
     */
    SysConfigModel selectByConfigId(@Param("configId") String config_id);


    /**
     * 编辑
     */
    int updateSysConfig(@Param("sysConfigModel") SysConfigModel sysConfigModel);
}
