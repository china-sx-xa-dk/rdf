package com.sxgokit.rdf.config.component;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public final class IpComponent {
    
    private static final String PROXS[] = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"};
    
    public String getIpAddress(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        String ip = null;
        for (String prox : PROXS) {
            ip = request.getHeader(prox);
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
                continue;
            } else {
                break;
            }
        }
        if (StringUtils.isBlank(ip)) {
            return request.getRemoteAddr();
        }
        return ip;
    }
}