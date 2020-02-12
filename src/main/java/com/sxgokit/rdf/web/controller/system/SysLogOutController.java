package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户退出Controller
 * @author DuKang
 */
@Controller
@RequestMapping("logout")
public class SysLogOutController extends BaseController {
    /**
     * 退出登录
     */
    @RequestMapping("")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(DataPool.SESSION_USER_PER);
        request.getSession().removeAttribute(DataPool.SESSION_USER);
        request.getSession().removeAttribute(DataPool.MENU_ID);
        return "login";
    }
}
