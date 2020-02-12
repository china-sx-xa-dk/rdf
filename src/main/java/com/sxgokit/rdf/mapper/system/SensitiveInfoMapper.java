/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 11:29:46)
 */
package com.sxgokit.rdf.mapper.system;

import java.util.List;
import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.sxgokit.rdf.model.vo.system.SensitiveInfoVo;
import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;
import com.sxgokit.rdf.model.condition.system.SensitiveInfoCondition;

/**
 * SensitiveInfoMapper
 * @author wgl
 * @date 2019-07-29 11:29:46
 */
@Repository
public interface SensitiveInfoMapper extends BaseMapper<SensitiveInfoModel> {

        List<SensitiveInfoVo> findPageList(@Param("page") Page page, @Param("condition") SensitiveInfoCondition condition);

        List<SensitiveInfoVo> findList(@Param("condition") SensitiveInfoCondition condition);
}