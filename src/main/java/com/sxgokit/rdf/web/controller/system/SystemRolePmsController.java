package com.sxgokit.rdf.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.sxgokit.rdf.service.system.SystemRolePmsService;
import com.sxgokit.rdf.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 角色菜单权限Controller
 * @author DuKang
 */
@Controller
@RequestMapping("rolePms")
public class SystemRolePmsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SystemRolePmsController.class);

    /**
     * 角色菜单权限service
     */
    @Autowired
    private SystemRolePmsService rolePmsService;

    /**
     * 授权时菜单树数据读取
     */
    @RequestMapping("getRolePmsList")
    @ResponseBody
    public String getRolePmsList(Integer roleId) {
        try {
            Map map = rolePmsService.getPmsIdsByRoleId(roleId);
            return JSONObject.toJSONString(map);
        } catch (Exception e) {
            logger.error("获取菜单数据错误：" + e.getMessage(), e);
            return JSON_FAIL;
        }
    }

    /**
     * 角色授权
     */
    @RequestMapping("doRoleGrant")
    @ResponseBody
    public String doRoleGrant(String permissionIds, Integer roleId) {
        try {
            if (StringUtils.isNotEmpty(permissionIds)) {
                rolePmsService.insertSelective(permissionIds, roleId);
            } else {
                return FAILED;
            }
            return SUCCESS;
        } catch (Exception e) {
            logger.error("角色菜单授权错误：" + e.getMessage(), e);
            return FAILED;
        }
    }
}
