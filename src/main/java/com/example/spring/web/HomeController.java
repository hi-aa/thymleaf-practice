package com.example.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.spring.config.Login;
import com.example.spring.login.dto.LoginDto;
import com.example.spring.login.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	/**
	 * 메인화면
	 * @param loginMember
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(@Login MemberDto loginMember, Model model) {
		if(loginMember == null) {
			return "home";
		}
//		model.addAttribute("member", loginMember);
		return "loginHome";
	}

	/**
	 * 로그인 페이지
	 * @param loginDto
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(@ModelAttribute("login") LoginDto loginDto, Model model) {
		model.addAttribute("login", loginDto);
		return "login";
	}
}
