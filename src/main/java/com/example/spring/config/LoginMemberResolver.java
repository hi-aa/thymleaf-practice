package com.example.spring.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.spring.login.LoginConst;
import com.example.spring.member.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginMemberResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("[LoginMemberResolver] supportsParameter");

		boolean hasLogin = parameter.hasParameterAnnotation(Login.class); // @Login 어노테이션 체크
		boolean hasMemberType = MemberDto.class.isAssignableFrom(parameter.getParameterType()); // 파라메터의 타입 체크
		return hasLogin && hasMemberType;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		log.info("[LoginMemberResolver] resolveArgument");

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);
		if(session == null) {
			return null;
		}
		// get MemberDto from login session
		return session.getAttribute(LoginConst.LOGIN_MEMBER);
	}

}
