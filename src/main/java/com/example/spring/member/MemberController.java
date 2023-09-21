package com.example.spring.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring.config.Login;
import com.example.spring.login.LoginConst;
import com.example.spring.member.dto.MemberDto;
import com.example.spring.member.dto.MemberUpdateDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

/**
 * 회원정보 관련 작업
 * @author : hi-aa
 * @date   : 2023-09-21
 */
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

	/**
	 * 프로필 화면
	 * @param loginMember
	 * @param model
	 * @return
	 */
	@GetMapping("/member/profile")
	public String memberProfilePage(@Login MemberDto loginMember, Model model) {
		model.addAttribute("profile", loginMember);
		return "member/profile";
	}

	/**
	 * 프로필 업데이트
	 * @param loginMember
	 * @param profile
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/member/profile")
	public String memberProfileUpdate(@Login MemberDto loginMember
			, @ModelAttribute(name = "profile") @Validated MemberUpdateDto profile
			, BindingResult bindingResult
			, HttpServletRequest request
			, Model model) {
		if(bindingResult.hasErrors()) {
			return "/member/profile";
		}

		MemberDto checkMemberId = memberRepository.getMemberByLoginId(profile.getLoginId()).get();
		if(checkMemberId == null || checkMemberId.getId() != loginMember.getId()) {
			throw new IllegalArgumentException("유효하지 않은 멤버 정보");
		}

		// update
		MemberDto resMember = memberRepository.updateMember(checkMemberId.getId(), profile);

		HttpSession session = request.getSession();
		session.setAttribute(LoginConst.LOGIN_MEMBER, resMember);

		return "redirect:/member/profile";
	}
}
