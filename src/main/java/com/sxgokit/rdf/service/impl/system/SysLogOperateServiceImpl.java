package com.sxgokit.rdf.service.impl.system;

import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.config.component.SnowflakeIdWorker;
import com.sxgokit.rdf.mapper.system.SysLogOperateDao;
import com.sxgokit.rdf.model.condition.system.SysLogOperateCondition;
import com.sxgokit.rdf.model.domain.system.SysLogOperateModel;
import com.sxgokit.rdf.model.vo.system.SysLogOperateVo;
import com.sxgokit.rdf.service.system.SysLogOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: liwei
 * @Date: 2019/6/11/
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class SysLogOperateServiceImpl implements SysLogOperateService {
    @Autowired
    private SysLogOperateDao sysLogOperateDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public List<SysLogOperateVo> findPageList(Page page, SysLogOperateCondition condition){
        return  sysLogOperateDao.findPageList(page,condition);
    }

    @Override
    public SysLogOperateVo selectByLogId(String logId){
        return  sysLogOperateDao.selectByLogId(logId);
    }


    @Override
    public  int updateById(SysLogOperateModel sysLogOperateModel){
        sysLogOperateModel.setOperateTime(new Date());
        return sysLogOperateDao.updateSysLogOperate(sysLogOperateModel);
    }

    @Override
    public int insertById(SysLogOperateModel sysLogOperateModel){
        sysLogOperateModel.setOperateTime(new Date());
        sysLogOperateModel.setLogId(snowflakeIdWorker.nextId().getStringId());
        return sysLogOperateDao.insert(sysLogOperateModel);
    }
}
