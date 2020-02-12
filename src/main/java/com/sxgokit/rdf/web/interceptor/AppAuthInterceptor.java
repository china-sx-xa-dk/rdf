package com.sxgokit.rdf.web.interceptor;

import com.google.gson.Gson;
import com.sxgokit.rdf.annotation.OpenAnnotationMethod;
import com.sxgokit.rdf.annotation.OpenAnnotationController;
import com.sxgokit.rdf.config.component.TokenComponent;
import com.sxgokit.rdf.config.entity.ResponseCode;
import com.sxgokit.rdf.model.dto.ResponseBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: dukang
 * Created: 2018/3/19
 */
@Slf4j
@Data
public class AppAuthInterceptor extends HandlerInterceptorAdapter {

    private Gson gson;

    private TokenComponent tokenComponent;

    public static final String HEAD_AUTH_TOKEN = "Auth-Token";

    public AppAuthInterceptor(TokenComponent tokenComponent, Gson gson) {
        this.tokenComponent = tokenComponent;
        this.gson = gson;
    }

    public String parseTokenFromRequest(HttpServletRequest request) {
        String sign = request.getParameter("sign");
        if (StringUtils.isNotEmpty(sign)) {
            return StringUtils.substringAfter(sign, ":");
        } else {
            return null;
        }
    }

    public String getTokenByRequest(HttpServletRequest request){
        return parseTokenFromRequest(request);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        OpenAnnotationMethod annotationMethod = method.getMethodAnnotation(OpenAnnotationMethod.class);
        OpenAnnotationController annotationForController = method.getMethod().getDeclaringClass().getAnnotation(OpenAnnotationController.class);
        if (annotationMethod != null && annotationMethod.value()) {
            return true;
        }else if(annotationForController != null && annotationForController.value() ){
            return true;
        } else {
            String authToken = request.getHeader(HEAD_AUTH_TOKEN);
            if (StringUtils.isEmpty(authToken)) {
                authToken = this.parseTokenFromRequest(request);
            }
            if (tokenComponent.checkToken(authToken)) {
                tokenComponent.keepaliveToken(authToken);
                return true;
            } else {
                ResponseBean responseBean = new ResponseBean();
                responseBean.setCode(Integer.valueOf(ResponseCode.App_Token_Failed.getCode()));
                responseBean.setMsg(ResponseCode.App_Token_Failed.getMessage());
                try {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    response.getWriter().write(gson.toJson(responseBean));
                } catch (IOException e) {
                    log.error("Write No Auth message exception.", e);
                }
                return false;
            }
        }
    }
}
