package com.example.spring.member;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring.login.dto.MemberDto;
import com.example.spring.login.dto.MemberRepository;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;

	/**
	 * 회원가입 페이지
	 * @return
	 */
	@GetMapping("/member/join")
	public String memberJoinPage(@ModelAttribute(name = "member") MemberDto MemberDto) {
		return "member/join";
	}

	/**
	 * 회원가입
	 * @param memberDto
	 * @return
	 */
	@PostMapping("/member/join")
	public String memberJoin(@ModelAttribute(name = "member") @Validated @NotNull MemberDto memberDto) {
		memberRepository.save(memberDto);
		return "redirect:/";
	}
}
