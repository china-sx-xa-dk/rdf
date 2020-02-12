package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.config.entity.WebServiceCallBack;
import com.sxgokit.rdf.model.domain.system.SysDict;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.model.dto.ResponseBean;
import com.sxgokit.rdf.service.system.*;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.util.DictUtils;
import com.sxgokit.rdf.util.redisUtil.RedisUtil;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 数据字典Controller
 * @author liwei
 */
@Controller
@RequestMapping("dict")
public class SysDictController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysDictController.class);

    /**
     * 数据字典service
     */
    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 刷新系统配置的开关
     */
    @Value("${rdf.refreshDictSwitch}")
    private boolean refreshDictSwitch = false;

    /**
     * 查询数据字典列表
     */
    @RequestMapping("/findDictList")
    public String findDictList(HttpServletRequest request)
    {

        //检索参数封装
        SysDict sysDict = new SysDict();
        //数据值
        sysDict.setValue(request.getParameter("value"));
        //标签名
        sysDict.setLabel(request.getParameter("label"));
        //类型
        sysDict.setType(request.getParameter("type"));
        try{
            // ==========================分页固定写法↓↓↓============================
            // 分页固定写法，需放在list数据查询前
            Page page = new Page();
            String currentPages = request.getParameter("currentPage");
            String showCounts = request.getParameter("showCount");
            if (StringUtils.isNotEmpty(currentPages)) {
                page.setCurrentPage(Integer.parseInt(currentPages));
            }
            if (StringUtils.isNotEmpty(showCounts)) {
                page.setShowCount(Integer.parseInt(showCounts));
            }
            // 默认每页显示十条，如需改变，设置ShowCount
            // page.setShowCount(20);
            // ==========================分页固定写法↑↑↑============================
            List sysDictList = sysDictService.findPageList(page,sysDict);

            request.setAttribute("page", page);
            request.setAttribute("sysDictList",sysDictList);
            request.setAttribute("sysDict",sysDict);

        }
        catch(Exception e)
        {
            logger.error("获取用戶信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/dict_list";
    }

    /**
     * 删除数据
     */
    @RequestMapping("/deleteSysDict")
    public String deleteSysDict(Integer id, RedirectAttributes redirectAttributes) {
        try {
            sysDictService.deleteById(id);
            addMessage(redirectAttributes, "删除成功");
            refreshRedis_sysDict();
        } catch (Exception e) {
            logger.error("数据字典删除失败：" + e.getMessage(), e);
            addMessage(redirectAttributes, "删除失败");
        }
        return "redirect:" + "/dict/findDictList?repage";
    }


    /**
     * 添加、编辑页面跳转
     * @param id 数据字典主键
     */
    @RequestMapping("/goAddOrEdit")
    public String goAddOrEdit(Integer id, String type, HttpServletRequest request) {
        try {
            //获取数据字典信息
            SysDict sysDict = new SysDict();
            if(null != id)
            {
                sysDict = sysDictService.selectById(id);
                if(StringUtils.isNotBlank(type))
                {
                    sysDict.setId(null);
                    sysDict.setValue("");
                    sysDict.setLabel("");
                    sysDict.setSort(sysDict.getSort()+1);
                }
            }
            request.setAttribute("data_dict",sysDict);
        } catch (Exception e) {
            logger.error("获取数据字典数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/dict_edit";
    }

    /**
     * 添加、编辑数据字典数据
     * @param sysDict
     */
    @RequestMapping(value = "/doAddOrEdit")
    public String doAddOrEdit(SysDict sysDict, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        SystemAdmin admin = currentAdmin(request);

        try {
            if (sysDict.getId() != null) {
                // 修改人
                sysDict.setUpdateBy(admin.getAdminId());
                // 修改时间
                sysDict.setUpdateDate(new Date());
                sysDictService.updateSysDict(sysDict);
            } else {
                // 创建人
                sysDict.setCreateBy(admin.getAdminId());
                // 创建时间
                sysDict.setCreateDate(new Date());
                // 修改人
                sysDict.setUpdateBy(admin.getAdminId());
                // 修改时间
                sysDict.setUpdateDate(new Date());
                sysDict.setDelFlag(0);
                sysDict.setParentId("0");
                sysDictService.insertSysDict(sysDict);
            }

            refreshRedis_sysDict();
        } catch (Exception e) {
            addMessage(model, "数据字典保存失败");
            request.setAttribute("data_dict",sysDict);
            return "page_system/dict_edit";
        }
        addMessage(redirectAttributes,
                "数据字典保存成功");
        return "redirect:" +  "/dict/findDictList?repage";
    }

    /**
     * 主动触发刷洗数据缓存
     */
    @PostMapping("/refreshRedisSysDictHand")
    @ResponseBody
    public ResponseBean refreshRedis_sysDict_hand() {
        if(refreshDictSwitch){
            //清空redis中所有数据字典
            redisUtil.del(DictUtils.redis_dict_list_key);
            redisUtil.lSet(DictUtils.redis_dict_list_key, sysDictService.findAll());
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 刷新数据字典缓存
     */
    private void refreshRedis_sysDict(){
        if(refreshDictSwitch){
            //清空redis中所有数据字典
            redisUtil.del(DictUtils.redis_dict_list_key);
            redisUtil.lSet(DictUtils.redis_dict_list_key, sysDictService.findAll());
        }
    }

}
