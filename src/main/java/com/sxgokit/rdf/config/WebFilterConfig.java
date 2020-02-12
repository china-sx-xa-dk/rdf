package com.sxgokit.rdf.config;

import com.sxgokit.rdf.config.filter.WebFilterParam;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dukang
 * @version 1.0.0
 * @ClassName WebFilterConfig.java
 * @createTime 2019年08月13日 11:12:00
 */
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean WebFilterDemo(){
        //配置过滤器
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new WebFilterParam());
//      例:frBean.addUrlPatterns("/app/*","/system/*"...);
        frBean.addUrlPatterns("/justUseHold/*");    // 这个路径仅仅用来占位,敏感词功能必须初始化配置过滤路径
        //springBoot会按照order值的大小，从小到大的顺序来依次过滤。
        frBean.setOrder(0);
        return frBean;
    }
}
