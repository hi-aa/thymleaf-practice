package com.example.spring.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.spring.interceptor.LoginCheckInterceptor;

import jakarta.servlet.SessionTrackingMode;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginMemberResolver());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(
				"/img/**"
				, "/css/**"
				, "/js/**"
				, "/error-page/**")
				.addResourceLocations(
						"classpath:/static/img/"
						, "classpath:/static/css/"
						, "classpath:/static/js/"
						, "classpath:/static/error-page/");
	}

	@Bean
	public ServletContextInitializer configSession() {
		return servletContext ->
		{
			// url 말고 cookie로 세션 접근
			servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
			// cookie는 http로만 쿠키 접근
			servletContext.getSessionCookieConfig().setHttpOnly(true);
		};
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/img/**", "/css/**", "/js/**", "/*.ico", "/error", "/error-page/**"
						,"/" , "/login", "/logout", "/member/join");
	}

}
