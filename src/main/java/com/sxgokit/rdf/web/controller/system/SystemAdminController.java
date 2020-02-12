package com.sxgokit.rdf.web.controller.system;

import com.google.common.collect.Lists;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.model.vo.app.AppUserVo;
import com.sxgokit.rdf.service.system.*;
import com.sxgokit.rdf.util.ExcelUtil.ExcelUtils;
import com.sxgokit.rdf.util.codeUtil.MD5.MD5Util;
import com.sxgokit.rdf.util.dateUtil.DateUtil;
import com.sxgokit.rdf.util.idCardUtil.IdcardUtils;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户信息Controller
 * @author DuKang
 */
@Controller
@RequestMapping("admin")
public class SystemAdminController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SystemAdminController.class);

    private SystemAdmin admin;

    /**
     * 用户信息service
     */
    @Autowired
    private SystemAdminService adminService;

    /**
     * 角色service
     */
    @Autowired
    private SystemRoleService roleService;

    /**
     * 用户角色service
     */
    @Autowired
    private SystemAdminRoleService adminrRoleService;

    /**
     * 权限service
     */
    @Autowired
    private SystemPermissionService permissionService;

    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 查询用户列表
     */
    @RequestMapping("/findAdminList")
    public String findAdminList(HttpServletRequest request) {
        // 检索参数封装
        SystemAdmin admin = new SystemAdmin();
        // 登录名
        admin.setLoginName(request.getParameter("loginName"));
        // 真实名
        admin.setAdminName(request.getParameter("adminName"));
        // 所属区域ID
        admin.setOrgId(request.getParameter("orgId"));
        // 所属企业ID
        admin.setCompany(request.getParameter("company"));
        // 状态
        String adminState = request.getParameter("adminState");
        if (StringUtils.isNotEmpty(adminState)) {
            admin.setAdminState(Integer.parseInt(adminState));
        }
        //公司名
        admin.setCompanyName(request.getParameter("companyName"));

        try {
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
            List adminList = adminService.findPageList(page, admin);

            request.setAttribute("page", page);
            request.setAttribute("admin", admin);
            request.setAttribute("data_list", adminList);
            //获取当前管理员组织机构（用于获取组织机构数据权限）
            SystemAdmin adminUser = currentAdmin(request);
            Integer orgId = Integer.parseInt(adminUser.getOrgId());
            //获取组织机构树
            String orgTreeListStr = sysOrganizationService.getOrgTreeData(orgId);
            request.setAttribute("orgTreeList", orgTreeListStr);
        } catch (Exception e) {
            logger.error("获取用戶信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/admin_list";
    }

    /**
     * 添加、编辑页面跳转
     *
     * @param adminId 用户ID
     */
    @RequestMapping("/goAddOrEdit")
    public String goAddOrEdit(Integer adminId, HttpServletRequest request) {
        try {
            //获取当前管理员组织机构（用于获取组织机构数据权限）
            SystemAdmin adminUser = currentAdmin(request);
            Integer orgId = Integer.parseInt(adminUser.getOrgId());
            if(adminId != null){
                Map adminMap = adminService.selectByPrimaryKey(adminId);
                request.setAttribute("data_admin", adminMap);
            }
            //获取角色列表
            List roleList = roleService.findUsingList();
            request.setAttribute("roleList", roleList);
            //获取组织机构树
            String orgTreeListStr = sysOrganizationService.getOrgTreeData(orgId);
            request.setAttribute("orgTreeList", orgTreeListStr);
        } catch (Exception e) {
            logger.error("获取用户信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/admin_edit";
    }

    /**
     * 添加、编辑用户
     *
     * @param admin 用户信息
     */
    @RequestMapping(value = "/doAddOrEdit", produces = "text/html;charset=utf-8")
    public String doAddOrEdit(SystemAdmin admin, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotBlank(admin.getLoginName())) {
            SystemAdmin systemAdmin = adminService.checkLoginName(admin.getLoginName(), admin.getAdminId());
            if (null != systemAdmin) {
                logger.error("用户信息保存失败：重复登录名");
                addMessage(model, "用户信息保存失败：重复登录名");
                return reAddOrEdit(admin,request);
            }
        }
        try {
            admin.setAdminBirthday(IdcardUtils.getBirthDayByIdCard(admin.getAdminCardno()));
            if (admin.getAdminId() != null) {
                adminService.updateAdminAndRole(admin);
            } else {
                adminService.insertSelective(admin);
            }
        } catch (Exception e) {
            logger.error("用户信息保存失败：" + e.getMessage(), e);
            addMessage(model, "用户信息保存失败");
            return reAddOrEdit(admin,request);
        }
        addMessage(redirectAttributes,
                "保存用户'" + admin.getLoginName() + "'成功");
        return "redirect:" +  "/admin/findAdminList";
    }

    @RequestMapping("/reAddOrEdit")
    public String reAddOrEdit(SystemAdmin admin, HttpServletRequest request) {
        try {
            //获取当前管理员组织机构（用于获取组织机构数据权限）
            SystemAdmin adminUser = currentAdmin(request);
            Integer orgId = Integer.parseInt(adminUser.getOrgId());
            request.setAttribute("data_admin", admin);
            //获取角色列表
            List roleList = roleService.findUsingList();
            request.setAttribute("roleList", roleList);
            //获取组织机构树
            String orgTreeListStr = sysOrganizationService.getOrgTreeData(orgId);
            request.setAttribute("orgTreeList", orgTreeListStr);
        } catch (Exception e) {
            logger.error("获取用户信息数据错误：" + e.getMessage(), e);
            return page_404;
        }
        return "page_system/admin_edit";
    }

    /**
     * 删除用户
     *
     * @param adminId 用户Id
     */
    @RequestMapping("/deleteAdmin")
    public String deleteAdmin(Integer adminId, RedirectAttributes redirectAttributes) {
        try {
            adminService.deleteByPrimaryKey(adminId);
            addMessage(redirectAttributes, "删除用户成功");
        } catch (Exception e) {
            logger.error("用户信息删除失败：" + e.getMessage(), e);
            addMessage(redirectAttributes, "删除用户失败");
        }
        return "redirect:" +  "/admin/findAdminList";
    }


    /**
     * 修改密码页面跳转
     */
    @RequestMapping("/goChangePass")
    public String goChangePass() {
        return "page_system/admin_pwd";
    }

    @RequestMapping("/doChangePass")
    @ResponseBody
    public String doChangPass(HttpServletRequest request) {
        try {
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            //密码MD5加密处理
            if (StringUtils.isNotEmpty(oldPass)) {
                oldPass = MD5Util.MD5_16_LowerCase(oldPass, DataPool.INPUT_CHARSET);
            }
            if (StringUtils.isNotEmpty(newPass)) {
                newPass = MD5Util.MD5_16_LowerCase(newPass, DataPool.INPUT_CHARSET);
            }

            //从session中获取已登录的用户信息
            SystemAdmin admin = (SystemAdmin) request.getSession().getAttribute(DataPool.SESSION_USER);
            //判断旧密码是否正确
            if (!admin.getLoginPass().equals(oldPass)) {
                return "opwdWrong";
            }
            //创建保存对象
            SystemAdmin model = new SystemAdmin();
            model.setAdminId(admin.getAdminId());
            model.setLoginPass(newPass);
            //执行修改操作
            adminService.updateSelective(model);

        } catch (Exception e) {
            logger.error("用户密码修改失败：" + e.getMessage(), e);
            return FAILED;
        }
        return SUCCESS;
    }


    /**
     * 导出数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/exportOut")
    public void exportOut(HttpServletRequest request, HttpServletResponse response) {
        if(admin == null)
        {
            admin = new SystemAdmin();
        }
        List list = adminService.findList(admin);
        String fileName = "用户数据" + DateUtil.getDate("yyyyMMddHHmmss")
                + ".xls";
        ExcelUtils.exportExcel(list,"用户信息","用户信息",SystemAdmin.class,fileName,response);
    }

    /**
     * 导入模板
     * @param response
     * @param request
     * @param redirectAttributes
     */
    @ResponseBody
    @RequestMapping(value = "/template")
    public void importFileTemplate(HttpServletResponse response,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes)
    {
            String fileName = "用户数据导入模板.xls";
            List list = Lists.newArrayList();
            ExcelUtils.exportExcel(list,"用户信息","用户信息",SystemAdmin.class,fileName,response);
    }

    /**
     * 数据导入
     * @param file
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/importExecl" , method = RequestMethod.POST)
    public String importExecl(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        try {
            if(null == file)
            {
                return "您尚未选择文件";
            }
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!suffix.equals("xls") && !suffix.equals("xlsx"))
            {
                return "请导入正确格式的文件，仅允许导入“xls”或“xlsx”格式文件";
            }
            List<SystemAdmin> list = ExcelUtils.importExcel(file, 1, 1, SystemAdmin.class);
            System.out.println("导入数据一共【"+list.size()+"】行");
            for (SystemAdmin user : list)
            {
                user.setLoginPass(MD5Util.MD5_16_LowerCase("123456", DataPool.INPUT_CHARSET));
                user.setDelflag(0);
                adminService.insertSelective(user);
            }
            if (list.size() > 0) {
                return"OK";
            } else {
                return "ERROR";
            }
        }
        catch (Exception e) {
            logger.error("用户导入失败：" + e.getMessage(), e);
            return "ERROR";
        }
    }
}
