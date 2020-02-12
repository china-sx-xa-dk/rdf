package com.sxgokit.rdf.web.controller.system;

import com.google.gson.Gson;
import com.sxgokit.rdf.config.component.IpComponent;
import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.service.system.SystemAdminService;
import com.sxgokit.rdf.service.system.SystemPermissionService;
import com.sxgokit.rdf.service.system.SystemRoleService;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.util.codeUtil.MD5.MD5Util;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户登录Controller
 * @author DuKang
 */
@Controller
@RequestMapping("/login")
public class SysLoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

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
     * 权限service
     */
    @Autowired
    private SystemPermissionService permissionService;

    @Autowired
    private IpComponent ipComponent;

    @Value("${rdf.server-session-timout}")
    private Integer sessionTimeOut = 1800;

    @Autowired
    private Gson gson;

    /**
     * 用户登录
     */
    @RequestMapping("")
    public String login(String userName, String password, HttpServletRequest request) {
        String errMsg = "";
        if (!StringUtils.isNotEmpty(userName)
                || !StringUtils.isNotEmpty(password)) {
            errMsg = "帐户名密码不能为空！";
            request.setAttribute("msg", errMsg);
            return "login";
        }

        request.setAttribute("userName", userName);

        /** 操作日志参数 */
        StringBuffer optDetail = new StringBuffer();
        optDetail.append("用户：" + userName + ";");
        optDetail.append("登录IP:" + ipComponent.getIpAddress(request) + ";");
        try {
            SystemAdmin model = new SystemAdmin();
            model.setLoginName(userName);
            model.setLoginPass(MD5Util.MD5_16_LowerCase(password, DataPool.INPUT_CHARSET));
            SystemAdmin admin = adminService.login(model);
            if (admin == null) {
                errMsg = "用户信息不正确！";
                request.setAttribute("msg", errMsg);
                return "login";
            } else if (admin.getAdminState() == 2) {
                errMsg = "用户已被禁用，请联系系统管理员！";
                request.setAttribute("msg", errMsg);
                return "login";
            } else if (checkIsBan(admin)) {
                errMsg = "用户所属角色有误，请联系系统管理员！";
                request.setAttribute("msg", errMsg);
                return "login";
            } else {
                // 查询权限
                List permissionList = permissionService.getUserPmsList(admin.getAdminId());
                logger.info(gson.toJson(permissionList));
                request.getSession().setAttribute(DataPool.SESSION_USER_PER, permissionList);
                request.getSession().setAttribute(DataPool.SESSION_USER, admin);
                // 更新用户登陆信息
                SystemAdmin adminUpd = new SystemAdmin();
                adminUpd.setAdminId(admin.getAdminId());
                adminUpd.setAdminLoginNum(admin.getAdminLoginNum() == null ? 1 : admin.getAdminLoginNum() + 1);
                adminUpd.setAdminLoginDate(new Date());
                adminUpd.setAdminLoginOlddate(admin.getAdminLoginDate());
                adminUpd.setAdminLoginIp(ipComponent.getIpAddress(request));
                adminUpd.setAdminLoginOldip(admin.getAdminLoginIp());
                adminService.updateSelective(adminUpd);
                /** 操作日志参数:执行状态 */
                request.getSession().setMaxInactiveInterval(sessionTimeOut);
                return "main";
            }
        } catch (Exception e) {
            logger.error("用户登录失败：" + e.getMessage(), e);
            errMsg = "登录失败，请稍后再试！";
            request.setAttribute("msg", errMsg);
            return "login";
        }
    }

    /**
     * 检查角色状态是否要限制登录
     *
     * @return false 可登录  true 不可登录
     */
    private boolean checkIsBan(SystemAdmin admin) {
        Map map = roleService.selectByPrimaryKey(admin.getRoleId());
        //没有查到不可登录
        if (map == null || map.isEmpty()) {
            return true;
            //角色状态无数据时不可登录
        } else if (map.get("roleState") == null) {
            return true;
        }
        //角色禁用时不可登录
        return Integer.parseInt(String.valueOf(map.get("roleState"))) == 1;
    }
}
