package com.sxgokit.rdf.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述:统一处理404异常
 *
 * @outhor ios
 * @create 2019-01-04 3:49 PM
 */
@Controller
public class NotFoundExceptionController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error(ModelMap map){
        map.addAttribute("message", "404 not found link！");
        return "page_404";
    }

}