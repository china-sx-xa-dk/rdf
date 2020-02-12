package com.sxgokit.rdf.web.controller.system;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sxgokit.rdf.model.domain.system.SysOrganization;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.service.system.SysOrganizationService;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @auther: DuKang
 * @date: 2019/5/22/0022 下午 17:40
 */
@RequestMapping("org")
@Controller
public class SysOrganizationPageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysOrganizationPageController.class);

    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 机构列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model,HttpServletRequest request)
    {
        //获取当前管理员组织机构（用于获取组织机构数据权限）
        SystemAdmin adminUser = currentAdmin(request);
        Integer orgId = Integer.parseInt(adminUser.getOrgId());
        List<SysOrganization> list = Lists.newArrayList();
        List<SysOrganization> sourcelist = sysOrganizationService.findList(orgId);
        //获取当前管理员组织机构上级组织ID，用于组织机构树列表数据构建
        for (SysOrganization org :sourcelist) {
            if(org.getOrgId().equals(orgId)){
                //设置第一级的组织机构上级ID为0，便于treeTable展示
                org.setParentId(0);
            }
        }
        SysOrganization.sortList(list, sourcelist, 0, true);
        model.addAttribute("list", list);
        return "page_system/org_list";
    }

    /**
     * 编辑/新增跳转
     * @param org
     * @param model
     * @return
     */
    @RequestMapping("/form")
    public String form(SysOrganization org, Model model)
    {
        if (org.getParent() == null || org.getParent().getOrgId() == null)
        {
            SysOrganization m = new SysOrganization();
            m.setOrgId(1);
            org.setParent(m);
        }
        org.setParent(sysOrganizationService.findById(org.getParent().getOrgId()));
        if (org.getOrgId() != null)
        {
            org = sysOrganizationService.findById(org.getOrgId());
        }
        model.addAttribute("org", org);
        return "page_system/org_form";
    }

    /**
     * 编辑/新增保存
     * @param org
     * @param model
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public String save(SysOrganization org, Model model,
                       RedirectAttributes redirectAttributes, HttpServletRequest request)
    {
        try {
            SystemAdmin user = currentAdmin(request);
            SysOrganization parent = sysOrganizationService.findById(org.getParent().getOrgId());
            if(org.getOrgId() != null)
            {
                org.setParentId(parent.getOrgId());
                org.setParentIds(parent.getParentIds()+parent.getOrgId()+",");
                sysOrganizationService.updateSelective(org);
            }
            else{
                org.setParentId(parent.getOrgId());
                org.setParentIds(parent.getParentIds()+parent.getOrgId()+",");
                sysOrganizationService.insertSelective(org);
            }
        } catch (Exception e) {
            addMessage(model, "组织机构保存失败");
            model.addAttribute("org", org);
            return "page_system/org_form";
        }
        addMessage(redirectAttributes,
                "组织机构保存成功");
        return "redirect:" +  "/org/list?repage";
    }

    /**
     * 删除组织机构
     * @param org
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/delete")
    public String delete(SysOrganization org, RedirectAttributes redirectAttributes)
    {
        //删除前判断是否存在绑定用户及下级机构
        sysOrganizationService.deleteByPrimaryKey(org.getOrgId());
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + "/org/list?repage";
    }

    /**
     * 获取组织机构树
     * @param extId
     * @param response
     * @return
     */
    @RequestMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData(@RequestParam(required = false)
                                                      String extId,  HttpServletResponse response,HttpServletRequest request)
    {
        //获取当前管理员组织机构（用于获取组织机构数据权限）
        SystemAdmin adminUser = currentAdmin(request);
        Integer orgId = Integer.parseInt(adminUser.getOrgId());
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<SysOrganization> list = sysOrganizationService.findList(orgId);
        for (int i = 0; i < list.size(); i++ )
        {
            SysOrganization e = list.get(i);
            if (StringUtils.isBlank(extId)
                    || (extId != null && !extId.equals(e.getOrgId() + "")))
            {
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getOrgId());
                map.put("pId", e.getParentId());
                map.put("name", e.getOrgName());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
