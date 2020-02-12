package com.sxgokit.rdf.web.controller;

import com.sxgokit.rdf.model.domain.system.SystemAdmin;
import com.sxgokit.rdf.common.DataPool;
import com.sxgokit.rdf.common.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {

    /**
     * 重定向
     **/
    protected final static String REQUEST_REDIRECT = "redirect:";

    /**
     * 成功页面
     **/
    protected final static String SUCCESS = "success";

    /**
     * 失败页面
     **/
    protected final static String FAILED = "failed";

    /**
     * 404页面
     **/
    protected final static String page_404 = "page_404";

    /**
     * 500页面
     **/
    protected final static String page_500 = "page_500";

    /**
     * json获取失败
     **/
    protected final static String JSON_FAIL = "{\"state\":\"failed\"}";

    protected HttpServletResponse response;

    @ModelAttribute
    public void setReqAndRes(/*HttpServletRequest request,*/ HttpServletResponse response) {
        this.response = response;
    }

    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    // AJAX输出，返回null
    public String ajax(String content, String type, HttpServletResponse response) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("ajax", e);
        }
        return null;
    }

    /**
     * 获取新的Page实例
     *
     * @return
     */
    protected Page newPage(HttpServletRequest request) {
        Page page = new Page();
        String currentPages = request.getParameter("currentPage");
        if (StringUtils.isNotEmpty(currentPages)) {
            page.setCurrentPage(Integer.parseInt(currentPages));
        }
        String showCount = request.getParameter("showCount");
        if (StringUtils.isNotEmpty(showCount)) {
            page.setShowCount(Integer.parseInt(showCount));
        }
        return page;
    }

    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("统一Controller异常捕获：" + e.getMessage(), e);
        return page_500;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public SystemAdmin currentAdmin(HttpServletRequest request) {
        return (SystemAdmin) request.getSession().getAttribute(DataPool.SESSION_USER);
    }

    /**
     * 添加Model消息
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages){
            sb.append(message).append(messages.length>1?"<br/>":"");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }
}
