package com.sxgokit.rdf.service.impl.system;


import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.config.component.SnowflakeIdWorker;
import com.sxgokit.rdf.mapper.system.SysConfigDao;
import com.sxgokit.rdf.model.condition.system.SysConfigCondition;
import com.sxgokit.rdf.model.domain.system.SysConfigModel;
import com.sxgokit.rdf.model.vo.system.SysConfigVo;
import com.sxgokit.rdf.service.system.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liwie
 * @Date: 2019/6/6
 * @Description:
 */


@Service
@Transactional
@Slf4j
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 添加
     * @param sysConfigModel
     * @return
     */
    @Override
    public int insertSysConfig(SysConfigModel sysConfigModel){
        sysConfigModel.setConfigId(snowflakeIdWorker.nextId().getStringId());
        return sysConfigDao.insertSysConfig(sysConfigModel);
    }

    /**
     * 编辑
     */
    @Override
    public int updateSysConfig(SysConfigModel sysConfigModel){
        return sysConfigDao.updateSysConfig(sysConfigModel);
    }

    /**
     * 查询列表（分页查询）
     */
    @Override
    public List<SysConfigVo> findPageList(Page page, SysConfigCondition sysConfigCondition){
        return sysConfigDao.findPageList(page,sysConfigCondition);
    }

    /**
     * 通过configId删除(物理删除)
     */
    @Override
    public int deleteByConfigId(String configId){
        return sysConfigDao.deleteById(configId);
    }

    /**
     * 查询（根据configId）
     */
    @Override
    public SysConfigModel selectById(String configId){
        return  sysConfigDao.selectByConfigId(configId);
    }




}
