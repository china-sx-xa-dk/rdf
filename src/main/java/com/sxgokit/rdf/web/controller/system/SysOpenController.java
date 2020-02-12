package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

/**
 * 左侧菜单展开Controller
 * @author DuKang
 */
@Controller
@RequestMapping("/putMenu")
public class SysOpenController extends BaseController {
    /**
     * 左边菜单回显
     */
    @RequestMapping("")
    @ResponseBody
    public String putMenu(HttpServletRequest request) {
        request.getSession().setAttribute(DataPool.MENU_ID, request.getParameter(DataPool.MENU_ID));
        request.getSession().setAttribute(DataPool.PERMISSION_FNAME, request.getParameter(DataPool.PERMISSION_FNAME));
        request.getSession().setAttribute(DataPool.PERMISSION_NAME, request.getParameter(DataPool.PERMISSION_NAME));
        return SUCCESS;
    }
}
