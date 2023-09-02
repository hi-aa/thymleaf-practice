package com.example.spring.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
				"/img/**",
				"/css/**",
				"/js/**")
				.addResourceLocations(
						"classpath:/static/img/",
						"classpath:/static/css/",
						"classpath:/static/js/");
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

}
