package com.sxgokit.rdf.config.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxgokit.rdf.util.common.StringUtils;
import com.sxgokit.rdf.util.sensitiveWordUtil.SensitiveWordUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * @author dukang
 * @version 1.0.0
 * @ClassName WebFilterParam.java
 * @createTime 2019年08月13日 11:06:00
 * 调用自定义过滤器,用于敏感词过滤
 * 重写了request和response的方法,仅对出入参进行操作
 * 继承OncePerRequestFilter保证在访问过程中只调用一次过滤器
 */
public class WebFilterParam extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
        ParameterServletRequestWrapper req = new ParameterServletRequestWrapper(request, parameterMap);
        ParameterServletResponseWrapper resp = new ParameterServletResponseWrapper(response);
        //调用对应的controller
        super.doFilter(req,resp,filterChain);
        //更换响应内容并输出
        out.print(SensitiveWordUtil.replaceSensitiveWord(new String(resp.getResponseData())));
        out.flush();
        out.close();
    }
}


