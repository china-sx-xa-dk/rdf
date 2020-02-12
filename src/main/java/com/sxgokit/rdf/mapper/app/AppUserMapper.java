package com.sxgokit.rdf.mapper.app;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.model.condition.app.AppUserCondition;
import com.sxgokit.rdf.model.vo.app.AppUserVo;
import com.sxgokit.rdf.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Dukang
        * @Date: 2019/5/29/0029 15:24
        * @Description:
        */
@Repository
public interface AppUserMapper extends BaseMapper<AppUserModel> {

    List<AppUserVo> findPageList(@Param("page") Page page,
                                 @Param("condition") AppUserCondition condition);

    List<AppUserVo> findList(@Param("condition") AppUserCondition appUserCondition);
}