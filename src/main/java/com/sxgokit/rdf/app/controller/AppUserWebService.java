package com.sxgokit.rdf.app.controller;

import com.google.gson.Gson;
import com.sxgokit.rdf.annotation.OpenAnnotationMethod;
import com.sxgokit.rdf.config.component.TokenComponent;
import com.sxgokit.rdf.config.entity.ResponseCode;
import com.sxgokit.rdf.config.entity.WebServiceCallBack;
import com.sxgokit.rdf.web.interceptor.AppAuthInterceptor;
import com.sxgokit.rdf.model.domain.app.AppUserModel;
import com.sxgokit.rdf.model.dto.ResponseBean;
import com.sxgokit.rdf.service.app.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dukang
 * @package com.sxgokit.app.control.controller
 * @date 2019/3/18 001816:56
 */
@RestController
@Slf4j
@RequestMapping("app/user")
public class AppUserWebService {

    @Autowired
    private TokenComponent tokenComponent;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private Gson gson;

    /**
     * 用户登录方法
     */
    @OpenAnnotationMethod
    @GetMapping("login/{loginName}/{password}")
    public ResponseBean login(@PathVariable final String loginName, @PathVariable final String password,
                              final HttpServletResponse servletResponse) {
        try{
            AppUserModel appUserModel = appUserService.login(loginName, password);
            if(appUserModel != null ){
                String token = tokenComponent.createToken(appUserModel);
                servletResponse.setHeader(AppAuthInterceptor.HEAD_AUTH_TOKEN, token);
                return WebServiceCallBack.succ(new TokenComponent.Token(token, gson.toJson(appUserModel)));
            }else{
                return WebServiceCallBack.autoBuild(ResponseCode.NOT_FOUND_USER_LOGIN_NAME.getCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return WebServiceCallBack.succ();
    }

    /**
     * 修改密码
     * @param oldPass
     * @param newPass
     * @param request
     * @return
     */
    @GetMapping("modifyPass")
    public ResponseBean modifyPass(@RequestParam final String oldPass,
                                 @RequestParam final String newPass,
                                 final HttpServletRequest request) {
        AppUserModel model = tokenComponent.getCurrentAppUser(request);
        String resCode = appUserService.modifyPass(model.getId(), oldPass, newPass);
        if(ResponseCode.Server_Handle_Successful.getCode().equalsIgnoreCase(resCode)){
            //修改成功,需要刷新用户缓存
            String authToken = request.getHeader(AppAuthInterceptor.HEAD_AUTH_TOKEN);
            AppUserModel cacheModel = appUserService.selectById(model.getId());
            tokenComponent.updateToken(authToken, cacheModel);
        }
        return WebServiceCallBack.autoBuild(resCode);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @GetMapping("loginOut")
    public ResponseBean loginOut(final HttpServletRequest request) {
        String authToken = request.getHeader(AppAuthInterceptor.HEAD_AUTH_TOKEN);
        tokenComponent.clearToken(authToken);
        return WebServiceCallBack.succ();
    }
}

