package com.sxgokit.rdf.config;

import com.google.gson.Gson;
import com.sxgokit.rdf.config.component.TokenComponent;
import com.sxgokit.rdf.web.interceptor.AppAuthInterceptor;
import com.sxgokit.rdf.web.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author:Dukang
 * @createDate:2018年9月10日
 * @desc:此类中申明拦截器、过滤器等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private Gson gson;

	@Autowired
	private TokenComponent tokenComponent;

	@Value("${app.auth-enabled}")
	private Boolean authEnabled = false;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		/**
		 * 注册session,放行app下路径,交给app的过滤器进行验证
		 */
		registry.addInterceptor(this.sessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/app/**","/login","/static/**");
		if (authEnabled) {
			registry.addInterceptor(this.appAuthInterceptor()).addPathPatterns("/app/**");
		}
	}

	private AppAuthInterceptor appAuthInterceptor() {
		return new AppAuthInterceptor(tokenComponent, gson);
	}

	private SessionInterceptor sessionInterceptor() { return new SessionInterceptor(); }

}
