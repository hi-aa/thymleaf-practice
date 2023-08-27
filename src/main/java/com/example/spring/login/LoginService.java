package com.example.spring.login;

import org.springframework.stereotype.Service;

import com.example.spring.login.dto.MemberDto;
import com.example.spring.login.dto.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;

	public MemberDto login(String id, String password) {
		return memberRepository.getMemberByLoginId(id)
				.filter(v -> v.getPassword().equals(password))
				.orElse(null);
	}
}
