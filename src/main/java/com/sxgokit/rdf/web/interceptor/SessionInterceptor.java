package com.sxgokit.rdf.web.interceptor;

import com.sxgokit.rdf.annotation.OpenAnnotationMethod;
import com.sxgokit.rdf.annotation.OpenAnnotationController;
import com.sxgokit.rdf.common.DataPool;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arhanhandlerdlerg2, Exception exception)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model)
            throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        OpenAnnotationMethod annotationMethod = method.getMethodAnnotation(OpenAnnotationMethod.class);
        OpenAnnotationController annotationForController = method.getMethod().getDeclaringClass().getAnnotation(OpenAnnotationController.class);
        if (annotationMethod != null && annotationMethod.value()) {
            return true;
        }else if(annotationForController != null && annotationForController.value() ){
            return true;
        } else {
            //从session中未获取到登录用户信息
            Object object = request.getSession().getAttribute(DataPool.SESSION_USER);
            if (null == object) {
                StringBuffer url = request.getRequestURL();
                String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
                response.sendRedirect(tempContextUrl+"login");
                return false;
            }
            return true;
        }
    }
}
