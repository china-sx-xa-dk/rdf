<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.web.controller.${modulepackage};

import java.util.Date;
import java.util.List;
import ${basepackage}.common.Page;
import com.google.common.collect.Lists;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ${basepackage}.model.dto.ResponseBean;
import ${basepackage}.service.system.SysOrganizationService;
import ${basepackage}.util.dateUtil.DateUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import ${basepackage}.util.ExcelUtil.ExcelUtils;
import ${basepackage}.web.controller.BaseController;
import ${basepackage}.model.domain.system.SystemAdmin;
import org.springframework.web.multipart.MultipartFile;
import ${basepackage}.config.entity.WebServiceCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ${basepackage}.model.vo.${modulepackage}.${className}Vo;
import ${basepackage}.service.${modulepackage}.${className}Service;
import ${basepackage}.model.domain.${modulepackage}.${className}Model;
import ${basepackage}.model.condition.${modulepackage}.${className}Condition;

/**
 * ${className}Controller
 * @author ${author}
 * @date ${now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Controller
@RequestMapping("${modulepackage}/${classNameLower}")
public class ${className}Controller extends BaseController{

    @Autowired
    private ${className}Service ${classNameLower}Service;

    <#if dataAuthorityType == '3'>
    @Autowired
    private SysOrganizationService sysOrganizationService;
    </#if>

    <#assign keyCloumn = ''>
    <#list table.columns as column>
        <#if column.pk>
            <#assign keyCloumn = column.columnNameLower>
        </#if>
    </#list>
    /**
     * 分页查询列表
     */
    @RequestMapping("get_list")
    public String get_list(${className}Condition condition,HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<${className}Vo> list;
        <#if dataAuthorityType == '2'>
        <#list table.columns as column>
        <#if (column.columnNameLower == 'createUser')>
        //只获取当前用户所属数据
        SystemAdmin admin = super.currentAdmin(request);
        condition.setCreateUser(admin.getAdminId());
        </#if>
        </#list>
        </#if>
        <#if dataAuthorityType == '3'>
        <#list table.columns as column>
        <#if (column.columnNameLower == 'orgId')>
        //获取当前用户所属组织的本级及所有下级数据
        SystemAdmin admin = super.currentAdmin(request);
        Integer orgId = Integer.parseInt(admin.getOrgId());
        Integer[] orgIdArray = sysOrganizationService.getAllOrgIdByOrg(orgId);
        condition.setOrgIdArray(orgIdArray);
        </#if>
        </#list>
        </#if>
        list = ${classNameLower}Service.findPageList(page, condition);

        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_manage/page_${modulepackage}/${classNameLower}_list";
    }

    /**
     * 跳转编辑页面
     */
    @GetMapping("jump_edit")
    public String jump_edit(@RequestParam(value = "${keyCloumn}", required = false) Integer ${keyCloumn}, HttpServletRequest request) {
        ${className}Model model = null;
        if (${keyCloumn} != null)
            model = ${classNameLower}Service.selectById(${keyCloumn});
        request.setAttribute("data", model == null ? new ${className}Vo() : model);
        return "page_manage/page_${modulepackage}/${classNameLower}_edit";
    }

    /**
     * 删除
     */
    @PostMapping("delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam(value = "${keyCloumn}", required = false) Integer ${keyCloumn}, HttpServletRequest request) {
        try {
        <#assign isHaveDelFlag = 0>
        <#list table.columns as column>
            <#if column.columnNameLower == 'delFlag'>
            <#assign isHaveDelFlag = 1>
            </#if>
        </#list>
        <#if isHaveDelFlag == 0>
            ${classNameLower}Service.deleteById(${keyCloumn});
        </#if>
        <#if isHaveDelFlag == 1>
            ${classNameLower}Service.deleteLogic(${keyCloumn});
        </#if>
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
    public ResponseBean saveEdit(@RequestBody final ${className}Model model, final HttpServletRequest request) {
        try{
            //获取当前登录用户信息
            SystemAdmin admin = super.currentAdmin(request);
            // set参数
            Date now = new Date();//当前时间

            <#list table.columns as column>
            <#if column.pk>
            if (model.get${column.columnName}() != null) { // 修改
            </#if>
            </#list>
            <#list table.columns as column>
            <#if column.isDateTimeColumn && (column.columnNameLower == 'updateTime')>
                model.setUpdateTime(now);
            </#if>
            </#list>
            <#list table.columns as column>
            <#if (column.columnNameLower == 'updateUser')>
                model.setUpdateUser(admin.getAdminId());
            </#if>
            </#list>
                ${classNameLower}Service.updateById(model);
            } else { // 添加
            <#list table.columns as column>
            <#if column.isDateTimeColumn && (column.columnNameLower == 'createTime')>
                model.setCreateTime(now);
            </#if>
            <#if column.columnNameLower == 'delFlag'>
                model.setDelFlag(0);
            </#if>
            </#list>
            <#list table.columns as column>
            <#if (column.columnNameLower == 'createUser')>
                model.setCreateUser(admin.getAdminId());
            </#if>
            </#list>
            <#list table.columns as column>
            <#if (column.columnNameLower == 'orgId')>
                //保存当前用户所属组织
                Integer orgId = Integer.parseInt(admin.getOrgId());
                model.setOrgId(orgId);
            </#if>
            </#list>
                ${classNameLower}Service.insert(model);
            }
        }catch (Exception e){
            logger.error("数据保存失败：" + e.getMessage(), e);
            return WebServiceCallBack.fail();
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 导出数据
     */
    @ResponseBody
    @RequestMapping(value ="/exportOut")
    public void exportOut(${className}Condition condition, HttpServletRequest request, HttpServletResponse response) {
        List list = ${classNameLower}Service.findList(condition);
        String fileName = "导出列表数据" + DateUtil.getDate("yyyyMMddHHmmss") + ".xls";
        ExcelUtils.exportExcel(list,"列表数据", "列表数据", ${className}Model.class, fileName, response);
    }

    /**
     * 导入模板
     */
    @ResponseBody
    @RequestMapping(value = "/template")
    public void importFileTemplate(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        String fileName = "数据导入模板.xls";
        List list = Lists.newArrayList();
        ExcelUtils.exportExcel(list, "导入列表数据", "导入列表数据", ${className}Model.class, fileName, response);
    }

    /**
     * 数据导入
     */
    @ResponseBody
    @RequestMapping(value = "/importExecl" , method = RequestMethod.POST)
    public String importExecl(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            if(null == file)
                return "您尚未选择文件";
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if(!suffix.equals("xls") && !suffix.equals("xlsx"))
                return "请导入正确格式的文件，仅允许导入“xls”或“xlsx”格式文件";
            List<${className}Model> list = ExcelUtils.importExcel(file, 1, 1, ${className}Model.class);
            System.out.println("导入数据一共【"+list.size()+"】行");
            for (${className}Model model : list){
                ${classNameLower}Service.insert(model);
            }
            if (list.size() > 0)
                return"OK";
            else
                return "ERROR";
        }catch (Exception e) {
            logger.error("数据导入失败：" + e.getMessage(), e);
            return "ERROR";
        }
    }
}