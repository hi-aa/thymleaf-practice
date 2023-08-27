package com.example.spring;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.example.spring.config.LoginMemberResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{

//	private final static String VIEWS = "/WEB-INF/views/";

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginMemberResolver());
	}

//	@Bean
//	public ITemplateResolver templateResolver() {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setPrefix(VIEWS);
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode(TemplateMode.HTML);
//		resolver.setCharacterEncoding("UTF-8");
//		resolver.setCacheable(false);
//		return resolver;
//	}

}
