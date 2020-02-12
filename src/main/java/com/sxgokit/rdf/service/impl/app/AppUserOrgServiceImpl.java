package com.sxgokit.rdf.service.impl.app;

import com.sxgokit.rdf.mapper.app.AppUserOrgMapper;
import com.sxgokit.rdf.model.domain.app.AppUserOrg;
import com.sxgokit.rdf.service.app.AppUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: wuhao
 * @Date: 2019年6月5日14:13:57
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class AppUserOrgServiceImpl implements AppUserOrgService {

    @Autowired
    private AppUserOrgMapper appUserOrgMapper;

    @Override
    public int insert(AppUserOrg appUserOrg) {
        return appUserOrgMapper.insert(appUserOrg);
    }

    @Override
    public int delete(AppUserOrg appUserOrg) {
        return appUserOrgMapper.delete(appUserOrg);
    }

}
