package com.sxgokit.rdf.mapper.app;

import com.sxgokit.rdf.model.domain.app.AppUserOrg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * app用户机构
 * @author wuhao
 * @date 2019/06/05
 */
@Mapper
@Repository
public interface AppUserOrgMapper {

    /**
     * [新增]
     * @author wuhao
     * @date 2019/06/05
     **/
    int insert(@Param("appUserOrg") AppUserOrg appUserOrg);

    /**
     * [刪除]
     * @author wuhao
     * @date 2019/06/05
     **/
    int delete(@Param("appUserOrg") AppUserOrg appUserOrg);

}
