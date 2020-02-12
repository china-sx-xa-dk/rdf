package com.sxgokit.rdf.web.controller.system;

import com.sxgokit.rdf.common.Page;
import com.sxgokit.rdf.model.condition.system.SysLogOperateCondition;
import com.sxgokit.rdf.model.vo.system.SysLogOperateVo;
import com.sxgokit.rdf.service.system.SysLogOperateService;
import com.sxgokit.rdf.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统日志控制层
 */

@Controller
@RequestMapping("sysLogOperate")
@Slf4j
public class SysLogOperateController extends BaseController {
    @Autowired
    private SysLogOperateService sysLogOperateService;

    /**
     * 查询系统日志列表
     */
    @RequestMapping("get_list")
    public String get_list(SysLogOperateCondition condition, HttpServletRequest request) {
        Page page = super.newPage(request); // 分页
        List<SysLogOperateVo> list;
        try {
            list = sysLogOperateService.findPageList(page, condition);
        } catch (Exception e) {
            log.error("error : {}", e.getMessage());
            return page_500;
        }
        request.setAttribute("data_list", list);
        request.setAttribute("page", page);
        request.setAttribute("condition", condition);

        return "page_system/sysLogOperate_list";
    }
}
