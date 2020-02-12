/*
 * http://www.sxgokit.com
 * Created By Dukang
 * Date By (2019-07-29 11:29:46)
 */
package com.sxgokit.rdf.web.controller.system;

import java.util.Date;
import java.util.List;

import com.sxgokit.rdf.annotation.OpenAnnotationMethod;
import com.sxgokit.rdf.common.Page;
import javax.servlet.http.HttpServletRequest;
import com.sxgokit.rdf.model.dto.ResponseBean;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import com.sxgokit.rdf.util.sensitiveWordUtil.SensitiveUtil;
import com.sxgokit.rdf.util.sensitiveWordUtil.SensitiveWordUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.sxgokit.rdf.web.controller.BaseController;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.config.entity.WebServiceCallBack;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxgokit.rdf.model.vo.system.SensitiveInfoVo;
import com.sxgokit.rdf.service.system.SensitiveInfoService;
import com.sxgokit.rdf.model.domain.system.SensitiveInfoModel;
import com.sxgokit.rdf.model.condition.system.SensitiveInfoCondition;

/**
 * SensitiveInfoController
 * @author wgl
 * @date 2019-07-29 11:29:46
 */
@Controller
@RequestMapping("system/sensitiveInfo")
public class SensitiveInfoController extends BaseController{

    @Autowired
    private SensitiveInfoService sensitiveInfoService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 测试敏感词
     */
    @RequestMapping("demo")
    @ResponseBody
    @OpenAnnotationMethod
    public ResponseBean demo(SensitiveInfoCondition condition,HttpServletRequest request) {
        return WebServiceCallBack.succ(condition);
    }

    /**
     * 分页查询列表
     */
    @RequestMapping("get_list")
    public String get_list(SensitiveInfoCondition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<SensitiveInfoVo> list;
        list = sensitiveInfoService.findPageList(page, condition);

        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_system/sensitiveInfo_list";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("jump_edit")
    public String jump_edit(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
        SensitiveInfoModel model = null;
        if (id != null)
            model = sensitiveInfoService.selectById(id);

        request.setAttribute("data", model == null ? new SensitiveInfoVo() : model);
        return "page_system/sensitiveInfo_edit";
    }

    /**
     * 删除
     */
    @PostMapping("delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
        try {
            sensitiveInfoService.deleteLogic(id);
            refreshRedis_sensitive();
        } catch (Exception e) {
            logger.error("数据删除失败：" + e.getMessage(), e);
            return WebServiceCallBack.fail();
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 保存信息
     */
    @PostMapping("save_edit")
    @ResponseBody
    public ResponseBean saveEdit(@RequestBody final SensitiveInfoModel model, final HttpServletRequest request) {
        try{
            //获取当前登录用户信息
            SystemAdmin admin = super.currentAdmin(request);
            // set参数
            Date now = new Date();//当前时间
            if (model.getId() != null) { // 修改
                model.setUpdateTime(now);
                model.setUpdateUser(admin.getAdminId());
                sensitiveInfoService.updateById(model);
                } else { // 添加
                    model.setDelFlag(0);
                    model.setCreateTime(now);
                    model.setCreateUser(admin.getAdminId());
                sensitiveInfoService.insert(model);
            }
            refreshRedis_sensitive();
        }catch (Exception e){
            logger.error("数据保存失败：" + e.getMessage(), e);
            return WebServiceCallBack.fail();
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 刷新敏感词缓存
     */
    private void refreshRedis_sensitive(){
        //清空redis中所有数据字典
        redisUtil.del(SensitiveUtil.redis_sensitive_list_key);
        //重新刷新系统敏感词库
        SensitiveWordUtil.init();
    }
}