package com.future.teamwork.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.future.teamwork.interceptor.LoginInterceptor;
import com.future.teamwork.interceptor.TestInterceptor;
import com.future.teamwork.test.DateConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	// 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns("/**") 表示拦截所有的请求，
		// excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为注册不需要登陆也可以访问
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login*","/static/**","/templates/**");
//		registry.addInterceptor(new TestInterceptor());
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {// 1
		registry.addConverter(new DateConverter());// 2
	}

}



