package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.model.domain.system.SystemRole;
import com.sxgokit.rdf.service.system.SystemRoleService;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 * @author DuKang
 */
@Controller
@RequestMapping("role")
public class SystemRoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SystemRoleController.class);

    /**
     * 角色service
     */
    @Autowired
    private SystemRoleService roleService;

    /**
     * 查询角色列表
     *
     * @return
     */
    @RequestMapping("/findRoleList")
    public String findRoleList(HttpServletRequest request) {
        SystemRole role = new SystemRole();
        role.setRoleName(request.getParameter("roleName"));
        String roleState = request.getParameter("roleState");
        if (StringUtils.isNotEmpty(roleState)) {
            role.setRoleState(Integer.parseInt(roleState));
        }
        try {
            Page page = new Page();
            String currentPages = request.getParameter("currentPage");
            if (StringUtils.isNotEmpty(currentPages)) {
                page.setCurrentPage(Integer.parseInt(currentPages));
            }
            List roleList = roleService.findPageList(page, role);
            // 分页工具类
            request.setAttribute("page", page);
            // 角色
            request.setAttribute("role", role);
            // 数据集
            request.setAttribute("data_list", roleList);
        } catch (Exception e) {
            logger.error("获取角色信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/role_list";
    }

    /**
     * 添加、编辑页面跳转
     *
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping("/goAddOrEdit")
    public String goAddOrEdit(Integer roleId, HttpServletRequest request) {
        try {
            SystemRole role = roleService.findById(roleId);
            request.setAttribute("data_role", role);
        } catch (Exception e) {
            logger.error("获取角色信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/role_edit";
    }

    /**
     * 添加、编辑角色
     *
     * @param role 角色信息
     * @return
     */
    @RequestMapping("/doAddOrEdit")
    public String doAddOrEdit(SystemRole role, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            SystemAdmin admin = (SystemAdmin) request.getSession()
                    .getAttribute(DataPool.SESSION_USER);
            if (role.getRoleId() != null) {
                // 修改人
                role.setRoleUpdateUser(admin.getAdminId());
                // 修改时间
                role.setRoleUpdateDate(new Date());
                roleService.updateSelective(role);
            } else {
                // 创建人
                role.setRoleCreateUser(admin.getAdminId());
                // 创建时间
                role.setRoleCreateDate(new Date());
                roleService.insertSelective(role);
            }
        } catch (Exception e) {
            addMessage(model, "角色信息保存失败");
            request.setAttribute("data_role", role);
            return "page_system/role_edit";
        }
        addMessage(redirectAttributes,
                "角色信息保存成功");
        return "redirect:" +  "/role/findRoleList?repage";
    }

    /**
     * 删除角色
     *
     * @param roleId 角色Id
     */
    @RequestMapping("/deleteRole")
    public String deleteAdmin(Integer roleId, RedirectAttributes redirectAttributes) {
        try {
            roleService.deleteByPrimaryKey(roleId);
        } catch (Exception e) {
            addMessage(redirectAttributes, "角色信息删除成功");
            return "redirect:" + "/org/list?repage";
        }
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + "/org/list?repage";
    }

    /**
     * 角色授权页面跳转
     *
     * @param roleId 角色Id
     */
    @RequestMapping("goRoleGrant")
    public String goRoleGrant(Integer roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        return "page_system/role_grant";
    }
}
