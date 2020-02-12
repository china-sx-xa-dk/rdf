/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 11:29:46)
 */
package com.sxgokit.rdf.service.system;

import java.util.List;
import com.sxgokit.rdf.common.Page;

import com.sxgokit.rdf.model.vo.system.SensitiveInfoVo;
import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;
import com.sxgokit.rdf.model.condition.system.SensitiveInfoCondition;

/**
 * SensitiveInfoService
 * @author wgl
 * @date 2019-07-29 11:29:46
 */
public interface SensitiveInfoService {

    /**
     * 新增
     */
    int insert(SensitiveInfoModel model);

    /**
     * 通过id删除
     */
    int deleteById(int id);

    /**
     * 通过id修改del_flag(删除标识),逻辑删除
     */
    int deleteLogic(int id);

    /**
     * 通过id进行更新
     */
    int updateById(SensitiveInfoModel model);

    /**
     * 通过id获取对应对象
     */
    SensitiveInfoModel selectById(int id);

    /**
     * 条件分页查询-未使用mybatis-plus
     */
    List<SensitiveInfoVo> findPageList(Page page, SensitiveInfoCondition condition);

    /**
     * 条件全部查询-用于导出
     */
    List<SensitiveInfoVo> findList(SensitiveInfoCondition condition);
}