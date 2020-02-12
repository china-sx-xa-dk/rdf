package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysConfigCondition;
import com.sxgokit.rdf.model.domain.system.SysConfigModel;
import com.sxgokit.rdf.model.vo.system.SysConfigVo;
import com.sxgokit.rdf.service.system.SysConfigService;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import com.sxgokit.rdf.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统配置控制层
 */
@Controller
@RequestMapping("sysConfig")
@Slf4j
public class SysConfigController extends BaseController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 刷新系统配置的开关
     */
    @Value("${rdf.refreshConfigSwitch}")
    private boolean refreshConfigSwitch = false;

    /**
     * 查询系统配置列表
     */
    @RequestMapping("get_list")
    public String get_list(SysConfigCondition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<SysConfigVo> list;
        list = sysConfigService.findPageList(page, condition);
        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_system/sysConfig_list";
    }

    /**
     * 添加、编辑页面跳转
     * @param configId 系统配置id
     */
    @RequestMapping("/goAddOrEdit")
    public String goAddOrEdit(@RequestParam(value = "configId", required = false) String configId,
                              HttpServletRequest request) {
        //获取系统配置信息
        SysConfigModel sysConfigModel = new SysConfigModel();
        if(StringUtils.isNotEmpty(configId)){
            sysConfigModel = sysConfigService.selectById(configId);
        }
        request.setAttribute("data_config",sysConfigModel);
        return "page_system/sysConfig_edit";
    }

    /**
     * 添加、编辑系统配置
     * @param sysConfigModel
     */
    @RequestMapping(value = "/doAddOrEdit")
    public String doAddOrEdit(SysConfigModel sysConfigModel, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
        try {
            if (sysConfigModel != null &&  StringUtils.isNotEmpty(sysConfigModel.getConfigId()))
            {
                sysConfigService.updateSysConfig(sysConfigModel);
            }
            else
            {
                sysConfigService.insertSysConfig(sysConfigModel);
            }
            //刷新缓存
            refreshRedis_sysConfig(sysConfigModel.getConfigKey(), sysConfigModel.getConfigValue(), 1);
        } catch (Exception e) {
            logger.error("系统配置保存失败：" + e.getMessage(), e);
            addMessage(model, "用户信息保存失败");
            return goAddOrEdit(sysConfigModel.getConfigId(),request);
        }
        addMessage(redirectAttributes, "保存系统配置成功");
        return "redirect:" +  "/sysConfig/get_list";
    }

    /**
     * 删除系统配置(物理删除)
     */
    @GetMapping("delete")
    public String delete(@RequestParam(value = "configId", required = false) String configId, RedirectAttributes redirectAttributes) {
        try {
            SysConfigModel model = sysConfigService.selectById(configId);
            sysConfigService.deleteByConfigId(configId);
            //刷新缓存
            refreshRedis_sysConfig(model.getConfigKey(), null, 2);
        } catch (Exception e) {
            logger.error("系统配置删除失败：" + e.getMessage(), e);
            addMessage(redirectAttributes, "删除用户失败");
        }
        addMessage(redirectAttributes, "删除系统配置成功");
        return "redirect:" +  "/sysConfig/get_list";
    }

    /**
     * 刷新系统缓存
     * @param type 1.更新 2.删除
     */
    private void refreshRedis_sysConfig(String key, String value, int type){
        if(refreshConfigSwitch){
            if (type == 1)
                redisUtil.set(key, value);
            else
                redisUtil.del(key);
        }
    }
}
