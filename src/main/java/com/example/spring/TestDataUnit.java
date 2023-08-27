package com.example.spring;

import org.springframework.stereotype.Component;

import com.example.spring.login.dto.MemberDto;
import com.example.spring.login.dto.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataUnit {

	private final MemberRepository memberRepository;

	/**
	 * 임의 데이터 생성
	 */
	@PostConstruct
	public void init() {
		MemberDto member1 = new MemberDto();
		member1.setLoginId("admin1");
		member1.setPassword("1234");
		member1.setFirstName("관");
		member1.setLastName("리자2");
		member1.setBirthYear(2000);
		member1.setBirthMonth(1);
		member1.setBirthDay(2);

		memberRepository.save(member1);
	}
}
