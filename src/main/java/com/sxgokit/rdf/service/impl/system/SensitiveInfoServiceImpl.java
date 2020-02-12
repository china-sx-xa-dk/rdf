/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 11:29:46)
 */
package com.sxgokit.rdf.service.impl.system;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.sxgokit.rdf.common.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Transactional;

import com.sxgokit.rdf.model.vo.system.SensitiveInfoVo;
import com.sxgokit.rdf.mapper.system.SensitiveInfoMapper;
import com.sxgokit.rdf.service.system.SensitiveInfoService;
import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;
import com.sxgokit.rdf.model.condition.system.SensitiveInfoCondition;

/**
 * SensitiveInfoServiceImpl
 * @author wgl
 * @date 2019-07-29 11:29:46
 */
@Service
@Transactional
@Slf4j
public class SensitiveInfoServiceImpl implements SensitiveInfoService {

    @Autowired
    private SensitiveInfoMapper sensitiveInfoMapper;

    @Override
    public int insert(SensitiveInfoModel model) {
        return sensitiveInfoMapper.insert(model);
    }

    @Override
    public int deleteById(int id) {
        return sensitiveInfoMapper.deleteById(id);
    }

    @Override
    public int deleteLogic(int id) {
        UpdateWrapper<SensitiveInfoModel> userModelUpdateWrapper = new UpdateWrapper<>();
        userModelUpdateWrapper.eq("id", id);
        userModelUpdateWrapper.set("del_flag", 1);
        return sensitiveInfoMapper.update(new SensitiveInfoModel(), userModelUpdateWrapper);
    }

    @Override
    public int updateById(SensitiveInfoModel model) {
        return sensitiveInfoMapper.updateById(model);
    }

    @Override
    public SensitiveInfoModel selectById(int id) {
        return sensitiveInfoMapper.selectById(id);
    }

    @Override
    public List<SensitiveInfoVo> findPageList(Page page, SensitiveInfoCondition condition) {
        return sensitiveInfoMapper.findPageList(page, condition);
    }

    @Override
    public List<SensitiveInfoVo> findList(SensitiveInfoCondition condition) {
        return sensitiveInfoMapper.findList(condition);
    }
}