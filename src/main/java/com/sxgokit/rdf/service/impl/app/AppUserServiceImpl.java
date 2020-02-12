package com.sxgokit.rdf.service.impl.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.config.component.SnowflakeIdWorker;
import com.sxgokit.rdf.config.entity.ResponseCode;
import com.sxgokit.rdf.config.exception.ResponseException;
import com.sxgokit.rdf.mapper.app.AppUserMapper;
import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.model.condition.app.AppUserCondition;
import com.sxgokit.rdf.model.vo.app.AppUserVo;
import com.sxgokit.rdf.service.app.AppUserService;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.util.codeUtil.MD5.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Dukang
 * @Date: 2019/5/29/0029 16:01
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public int insert(AppUserModel appUserModel) {
        try {
            appUserModel.setId(snowflakeIdWorker.nextId().getStringId());
            if(StringUtils.isNotEmpty(appUserModel.getLoginPassword())){
                appUserModel.setLoginPassword(MD5Util.MD5_16_LowerCase(appUserModel.getLoginPassword(), DataPool.INPUT_CHARSET));
            }
            else
            {
                appUserModel.setLoginPassword(MD5Util.MD5_16_LowerCase("123456", DataPool.INPUT_CHARSET));
            }

            return appUserMapper.insert(appUserModel);
        }catch (Exception e){
            //TODO:app用户新增密码加密抛出异常处理
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return appUserMapper.deleteById(id);
    }

    @Override
    public int deleteLogic(String id) {
        //修改del_flag标识为1
        UpdateWrapper<AppUserModel> userModelUpdateWrapper = new UpdateWrapper<AppUserModel>();
        userModelUpdateWrapper.eq("id", id);
        userModelUpdateWrapper.set("del_flag", 1);
        return appUserMapper.update(new AppUserModel(), userModelUpdateWrapper);
    }

    @Override
    public int updateById(AppUserModel appUserModel) {
        try {
            if(StringUtils.isNotEmpty(appUserModel.getLoginPassword())){
                appUserModel.setLoginPassword(MD5Util.MD5_16_LowerCase(appUserModel.getLoginPassword(), DataPool.INPUT_CHARSET));
            }
            else
            {
                appUserModel.setLoginPassword(MD5Util.MD5_16_LowerCase("123456", DataPool.INPUT_CHARSET));
            }
            return appUserMapper.updateById(appUserModel);
        }catch (Exception e){
            //TODO:更新appuser时候加密密码
        }
        return 0;
    }

    @Override
    public AppUserModel selectById(String id) {
        return appUserMapper.selectById(id);
    }

    @Override
    public List<AppUserModel> selectAll(AppUserCondition condition) {
        QueryWrapper<AppUserModel> queryWrapper = new QueryWrapper<>();
        //使用myvatisPlus实现dao
        return appUserMapper.selectList(queryWrapper);
    }

    @Override
    public List<AppUserVo> findPageList(Page page, AppUserCondition appUserCondition) {
        return appUserMapper.findPageList(page, appUserCondition);
    }

    @Override
    public List<AppUserVo> findList(AppUserCondition appUserCondition) {
        return appUserMapper.findList(appUserCondition);
    }

    @Override
    public AppUserVo login(String loginName, String passWord) {
        AppUserModel userModel = appUserMapper.selectOne(new QueryWrapper<AppUserModel>().eq("login_name", loginName));
        try {
            if (null == userModel) {
                throw new ResponseException(ResponseCode.NOT_FOUND_USER_LOGIN_NAME.getCode());
            }
            if (!StringUtils.equalsIgnoreCase(userModel.getLoginPassword(), MD5Util.MD5_16_LowerCase(passWord, DataPool.INPUT_CHARSET))) {
                throw new ResponseException(ResponseCode.PASSWORD_WRONG.getCode());
            }
            AppUserVo appUserVo = new AppUserVo();
            BeanUtils.copyProperties(userModel, appUserVo);
            return appUserVo;
        }catch (Exception e){
            //TODO:完成异常的捕获处理-login
        }
        return null;
    }

    @Override
    public String modifyPass(String appUserId, String oldPass, String newPass) {
        try{
            AppUserModel userModel = appUserMapper.selectById(appUserId);
            if (userModel != null && StringUtils.equalsIgnoreCase(userModel.getLoginPassword(), MD5Util.MD5_16_LowerCase(oldPass, DataPool.INPUT_CHARSET))) {
                appUserMapper.update(userModel, new UpdateWrapper<AppUserModel>().eq("id", appUserId).set("login_password", MD5Util.MD5_16_LowerCase(newPass, DataPool.INPUT_CHARSET)));
                return ResponseCode.Server_Handle_Successful.getCode();
            }else{
                return ResponseCode.PASSWORD_MODIFY.getCode();
            }
        }catch (Exception e){
            //TODO:完成异常的捕获处理-modifyPass
        }
        return null;
    }
}
