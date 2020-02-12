package com.sxgokit.rdf.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DuKang on 2018/3/18.
 */
@Controller
public class WelcomeController {

    Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/pro_list")
    public String imageUpload() {
        return "page_company/product_list_check";
    }

    @RequestMapping(value = "dispatch")
    public String danteDispatch(String page) {
        if (StringUtils.isEmpty(page)) {
            return "introduce";
        }
        return page;
    }

    @RequestMapping(value = "/media/downloadApp")
    public String appDownloadPage() {
        return "appDownLoad";
    }

    @RequestMapping(value = "/imgUpload")
    public String imgUpload() {
        return "imgUpload";
    }

    @RequestMapping(value = "/soundUpload")
    public String soundUpload() {
        return "soundUpload";
    }
}
