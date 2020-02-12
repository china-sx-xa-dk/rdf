package com.sxgokit.rdf.service.app;

import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.model.condition.app.AppUserCondition;
import com.sxgokit.rdf.model.vo.app.AppUserVo;
import com.sxgokit.rdf.common.Page;

import java.util.List;

/**
 * @Author: Dukang
 * @Date: 2019/5/29/0029 15:46
 * @Description: app登录用户操作接口
 */
public interface AppUserService {

    /**
     * 新增
     */
    int insert(AppUserModel appUserModel);

    /**
     * 通过id删除
     */
    int deleteById(String id);

    /**
     * 通过id修改del_flag(删除标识),逻辑删除
     */
    int deleteLogic(String id);

    /**
     * 通过id进行更新
     */
    int updateById(AppUserModel appUserModel);

    /**
     * 通过id获取对应对象
     */
    AppUserModel selectById(String id);

    /**
     * 根据查询条件获取所有对象
     */
    List<AppUserModel> selectAll(AppUserCondition condition);

    /**
     * 条件分页查询-未使用mybatis-plus
     */
    List<AppUserVo> findPageList(Page page, AppUserCondition condition);

    /**
     * 列表数据查询
     * @param condition
     * @return
     */
    List<AppUserVo> findList(AppUserCondition condition);

    /**
     * 登陆
     */
    AppUserVo login(String loginName, String passWord);

    /**
     * 修改密码
     * @param appUserId
     * @param oldPass
     * @param newPass
     * @return ResponseCode
     */
    String modifyPass(String appUserId, String oldPass, String newPass);

}
