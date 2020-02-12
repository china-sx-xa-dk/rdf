package com.sxgokit.rdf.web.controller.app;

import com.google.common.collect.Lists;
import com.sxgokit.rdf.config.entity.WebServiceCallBack;
import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.model.domain.app.AppUserOrg;
import com.sxgokit.rdf.model.condition.app.AppUserCondition;
import com.sxgokit.rdf.model.domain.system.SysOrganization;
import com.sxgokit.rdf.model.dto.ResponseBean;
import com.sxgokit.rdf.model.vo.app.AppUserVo;
import com.sxgokit.rdf.service.app.AppUserOrgService;
import com.sxgokit.rdf.service.app.AppUserService;
import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.service.system.SysOrganizationService;
import com.sxgokit.rdf.util.ExcelUtil.ExcelUtils;
import com.sxgokit.rdf.util.dateUtil.DateUtil;
import com.sxgokit.rdf.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import com.sxgokit.rdf.util.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * app用户控制层
 */
@Controller
@RequestMapping("web/appuser")
@Slf4j
public class AppUserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserOrgService appUserOrgService;

    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 查询app用户列表
     */
    @RequestMapping("get_list")
    public String get_list(AppUserCondition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<AppUserVo> list;
        try {
            list = appUserService.findPageList(page, condition);
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            return page_500;
        }
        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_manage/page_app/appuser_list";
    }


    /**
     * 跳转app用户编辑列表
     */
    @GetMapping("jump_edit")
    public String jump_edit(@RequestParam(value = "id", required = false) String id,
                                    HttpServletRequest request) {
        AppUserModel appUserModel = null;
        try {
            if (StringUtils.isNotEmpty(id)) {
                appUserModel = appUserService.selectById(id);
            }
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            return page_500;
        }
        request.setAttribute("data", appUserModel == null ? new AppUserVo() : appUserModel);
        return "page_manage/page_app/appuser_edit";
    }

    /**
     * 保存信息
     */
    @PostMapping("appuser_edit")
    @ResponseBody
    public ResponseBean appuserEdit(@RequestBody final AppUserModel appUser, final HttpServletRequest request) {
        boolean ret;
        try{
            // set参数
            Date now = new Date();//当前时间
            if (StringUtils.isNotEmpty(appUser.getId())) { // 修改
                appUser.setUpdateTime(now);
                ret = appUserService.updateById(appUser) > 0 ;
            } else { // 添加
                appUser.setCreateTime(now);
                ret = appUserService.insert(appUser) > 0;
            }
            // 验证结果
            if (!ret) { // 执行失败
                return WebServiceCallBack.fail();
            }
        }catch (Exception e){
            logger.error("保存用户失败：" + e.getMessage(), e);
            return WebServiceCallBack.fail();
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 删除app用户编辑列表
     */
    @GetMapping("delete")
    public String delete(@RequestParam(value = "id", required = false) String id,
                                  HttpServletRequest request,RedirectAttributes redirectAttributes) {
        try {
            appUserService.deleteLogic(id);
            addMessage(redirectAttributes, "删除用户成功");
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            addMessage(redirectAttributes, "删除用户失败");
        }
        return "redirect:" + "/web/appuser/get_list?repage";
    }

    /**
     * 用户机构跳转
     * @param model
     * @return
     */
    @RequestMapping(value = {"index"})
    public String index(Model model)
    {
        return "page_manage/page_app/appuserIndex";
    }


    /**
     * 用户机构查询用户
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String list(AppUserCondition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<AppUserVo> list;
        try {
            list = appUserService.findPageList(page, condition);
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            return page_500;
        }
        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_manage/page_app/appuserOrg";
    }

    /**
     * 移除用户
     * @param id
     * @param request
     * @return
     */
    @GetMapping("deleteMove")
    public String deleteMove(@RequestParam(value = "id", required = false) String id,@RequestParam(value = "orgId", required = false) Integer orgId,
                         HttpServletRequest request,RedirectAttributes redirectAttributes) {
        try {
            AppUserOrg appUserOrg = new AppUserOrg();
            appUserOrg.setOrgId(orgId);
            appUserOrg.setUserId(id);
            appUserOrgService.delete(appUserOrg);
            addMessage(redirectAttributes, "移除用户成功");
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            addMessage(redirectAttributes, "移除用户失败");
        }
        return "redirect:" +"/web/appuser/list?orgId="+orgId;
    }

    @RequestMapping("/addUser")
    public String addUser(AppUserCondition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<AppUserVo> list;
        SysOrganization sysOrganization;
        try {
            list = appUserService.findPageList(page, condition);
            sysOrganization = sysOrganizationService.findById(condition.getOrgIdNot());
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            return page_500;
        }
        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);
        request.setAttribute("org", sysOrganization);

        return "page_manage/page_app/appuserOrgAdd";
    }

    @RequestMapping("/addUserSave")
    public String addUserSave(AppUserCondition condition,HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(StringUtils.isNotBlank(condition.getIds()))
        {
            String[] idArray = condition.getIds().split(",");
            AppUserOrg appUserOrg = new AppUserOrg();
            for(int i=0; i<idArray.length; i++)
            {
                appUserOrg.setOrgId(condition.getOrgId());
                appUserOrg.setUserId(idArray[i]);
                appUserOrgService.insert(appUserOrg);
            }
        }
        else{
            addMessage(redirectAttributes,"保存失败，请至少选择一位用户");
            return "redirect:" +  "/web/appuser/addUser?orgIdNot="+condition.getOrgId();
        }
        addMessage(redirectAttributes,"保存成功");
        return "redirect:" +  "/web/appuser/list?orgId="+condition.getOrgId();
    }

    /**
     * 导出数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/exportOut")
    public void exportOut(AppUserCondition appUserCondition, HttpServletRequest request, HttpServletResponse response) {
        List list = appUserService.findList(appUserCondition);
        String fileName = "APP用户数据" + DateUtil.getDate("yyyyMMddHHmmss")
                + ".xls";
        ExcelUtils.exportExcel(list,"APP用户信息","APP用户信息",AppUserModel.class,fileName,response);
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
        String fileName = "APP用户数据导入模板.xls";
        List list = Lists.newArrayList();
        ExcelUtils.exportExcel(list,"APP用户信息","APP用户信息",AppUserModel.class,fileName,response);
    }

    /**
     * 数据导入
     * @param file
     * @return
     */
    @RequestMapping(value = "/importExecl" , method = RequestMethod.POST)
    public String importExecl(@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            if(null == file || file.isEmpty())
            {
                addMessage(redirectAttributes,"导入失败，您尚未选择文件");
                return "redirect:" + "/web/appuser/get_list?repage";
            }
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!suffix.equals("xls") && !suffix.equals("xlsx"))
            {
                addMessage(redirectAttributes,"导入失败，请导入正确格式的文件，仅允许导入“xls”或“xlsx”格式文件");
                return "redirect:" + "/web/appuser/get_list?repage";
            }
            List<AppUserModel> list = ExcelUtils.importExcel(file, 1, 1, AppUserModel.class);
            System.out.println("导入数据一共【"+list.size()+"】行");
            for (AppUserModel user : list)
            {
                user.setDelFlag(0);
                appUserService.insert(user);
            }
            if (list.size() > 0) {
                addMessage(redirectAttributes,"导入成功!");
            } else {
                addMessage(redirectAttributes,"导入失败!");
            }
        }
        catch (Exception e) {
            addMessage(redirectAttributes,"导入失败!");
        }
        return "redirect:" + "/web/appuser/get_list?repage";
    }
}
