package com.example.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring.login.dto.LoginDto;
import com.example.spring.login.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * 로그인
	 * @param loginDto
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public String login(@ModelAttribute("login") @Validated LoginDto loginDto
			, BindingResult bindingResult
			, @RequestParam(defaultValue = "/") String redirectURL
			, HttpServletRequest request) {
		log.info("login={}", loginDto);

		if(bindingResult.hasErrors()) {
			return "/login";
		}

		MemberDto loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());
		if(loginMember == null) {
			bindingResult.reject("loginFail", "아이디 또는 패스워드가 일치하지 않습니다.");
			return "/login";
		}

		// JSESSIONID // 없으면 신규 세션 생성
		HttpSession session = request.getSession();
		session.setAttribute(LoginConst.LOGIN_MEMBER, loginMember);

		return "redirect:" + redirectURL;
	}

	/**
	 * 로그아웃
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(LoginConst.LOGIN_MEMBER);
			session.invalidate();
		}

		return "redirect:/";
	}
}
