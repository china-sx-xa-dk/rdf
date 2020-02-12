package com.sxgokit.rdf.web.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.model.domain.system.SystemPermission;
import com.sxgokit.rdf.service.system.SystemPermissionService;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单Controller
 * @author DuKang
 */
@Controller
@RequestMapping("permission")
public class SystemPermissionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SystemPermissionController.class);

    /**
     * 菜单service
     */
    @Autowired
    private SystemPermissionService permissionService;

    /**
     * 页面跳转
     */
    @RequestMapping("/goMenuTree")
    public String goMenuTree() {
        return "page_system/menu_tree";
    }

    /**
     * 菜单列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,HttpServletRequest request)
    {
        List<SystemPermission> list = Lists.newArrayList();
        SystemAdmin user = currentAdmin(request);
        List<SystemPermission> sourcelist;
        if(user.getAdminId() == 1)
        {
            sourcelist = permissionService.findList(new SystemPermission());
        }
        else{
            SystemPermission m = new SystemPermission();
            m.setUserId(user.getAdminId());
            sourcelist = permissionService.findList(m);
        }
        SystemPermission.sortList(list, sourcelist, 1, true);
        model.addAttribute("list", list);
        return "page_system/menu_list";
    }

    /**
     * 编辑跳转
     * @param menu
     * @param model
     * @return
     */
    @RequestMapping("/form")
    public String form(SystemPermission menu, Model model)
    {
        if (menu.getParent() == null || menu.getParent().getPermissionId() == null)
        {
            SystemPermission m = new SystemPermission();
            m.setPermissionId(1);
            menu.setParent(m);
        }
        menu.setParent(permissionService.findById(menu.getParent().getPermissionId()));
        // 获取排序号，最末节点排序号+30
        if (menu.getPermissionId() == null)
        {
            List<SystemPermission> list = Lists.newArrayList();
            List<SystemPermission> sourcelist = permissionService.findList(new SystemPermission());
            SystemPermission.sortList(list, sourcelist, menu.getPermissionParentId(), false);
            if (list.size() > 0)
            {
                menu.setPermissionSort(list.get(list.size() - 1).getPermissionSort() + 30);
            }
        }else{
            menu = permissionService.findById(menu.getPermissionId());
        }
        model.addAttribute("menu", menu);
        return "page_system/menu_form";
    }

    /**
     * 编辑保存
     * @param menu
     * @param model
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public String save(SystemPermission menu, Model model,
                       RedirectAttributes redirectAttributes,HttpServletRequest request)
    {
        SystemAdmin user = currentAdmin(request);
        if (user.getAdminId() != 1)
        {
            addMessage(redirectAttributes, "越权操作失败，只有超级管理员才能添加或修改数据！");
            return "redirect:" +  "/permission/list?repage";
        }
        try {
            SystemPermission parent = permissionService.findById(menu.getParent().getPermissionId());
            if(menu.getPermissionId() != null)
            {
                menu.setPermissionParentId(parent.getPermissionId());
                menu.setParentIds(parent.getParentIds()+parent.getPermissionId()+",");
                permissionService.updateSelective(menu);
            }
            else{
                menu.setPermissionParentId(parent.getPermissionId());
                menu.setParentIds(parent.getParentIds()+parent.getPermissionId()+",");
                permissionService.insertSelective(menu);
            }
        } catch (Exception e) {
            addMessage(model, "菜单保存失败");
            model.addAttribute("menu", menu);
            return "page_system/menu_form";
        }
        addMessage(redirectAttributes,
                "菜单保存成功");
        return "redirect:" +  "/permission/list?repage";
    }

    /**
     * 删除菜单
     * @param menu
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/delete")
    public String delete(SystemPermission menu, RedirectAttributes redirectAttributes)
    {
        permissionService.deleteByPrimaryKey(menu.getPermissionId());
        addMessage(redirectAttributes, "菜单删除成功");
        return "redirect:" + "/permission/list?repage";
    }

    /**
     * 菜单树
     * @param extId
     * @param isShowHide
     * @param response
     * @return
     */
    @RequestMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData(@RequestParam(required = false)
                                                      String extId, @RequestParam(required = false)
                                                      String isShowHide, HttpServletResponse response)
    {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SystemPermission> list = permissionService.findList(new SystemPermission());
        for (int i = 0; i < list.size(); i++ )
        {
            SystemPermission e = list.get(i);
            if (StringUtils.isBlank(extId)
                    || (extId != null && !extId.equals(e.getPermissionId() + "") && e.getParentIds().indexOf(","+extId+",")==-1))
            {
                if (isShowHide != null && isShowHide.equals("0")
                        && e.getIsShow().equals("0"))
                {
                    continue;
                }
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getPermissionId());
                map.put("pId", e.getPermissionParentId());
                map.put("name", e.getPermissionName());
                mapList.add(map);
            }
        }
        return mapList;
    }


    /**
     * 查询菜单列表
     */
    @RequestMapping("/loadTree")
    @ResponseBody
    public String getTreeData() {
        try {
            List list = permissionService.getPermissionList();
            return JSONArray.toJSONString(list);
        } catch (Exception e) {
            logger.error("获取菜单数据错误：" + e.getMessage(), e);
            return JSON_FAIL;
        }
    }

    /**
     * 根据主键查询菜单信息
     */
    @RequestMapping("getDataById")
    @ResponseBody
    public String getTreeDataById(Integer permissionId) {
        try {
            Map map = permissionService.selectByPrimaryKey(permissionId);
            return JSONObject.toJSONString(map);
        } catch (Exception e) {
            logger.error("获取菜单数据错误：" + e.getMessage(), e);
            return JSON_FAIL;
        }
    }

    /**
     * 添加、编辑
     */
    @RequestMapping("doAddOrEdit")
    @ResponseBody
    public String doAddOrEdit(SystemPermission permission) {
        try {
            if (permission.getPermissionId() != null) {
                permissionService.updateSelective(permission);
            } else {
                permissionService.insertSelective(permission);
            }
        } catch (Exception e) {
            logger.error("保存菜单数据错误：" + e.getMessage(), e);
            return FAILED;
        }
        return SUCCESS;
    }

    /**
     * 根据主键删除菜单信息
     */
    @RequestMapping("delDataById")
    @ResponseBody
    public String delTreeDataById(Integer permissionId) {
        try {
            permissionService.deleteByPrimaryKey(permissionId);
        } catch (Exception e) {
            logger.error("删除菜单数据错误：" + e.getMessage(), e);
            return FAILED;
        }
        return SUCCESS;
    }

    /**
     * 左边菜单回显
     */
    @RequestMapping("putMenu")
    @ResponseBody
    public String putMenu(Integer menuId, HttpServletRequest request) {
        request.getSession().setAttribute(DataPool.MENU_ID, menuId);
        return SUCCESS;
    }
}
