package com.example.spring;

import org.springframework.stereotype.Component;

import com.example.spring.goods.GoodsRepository;
import com.example.spring.goods.dto.GoodsDto;
import com.example.spring.member.MemberRepository;
import com.example.spring.member.dto.MemberDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataUnit {

	private final MemberRepository memberRepository;

	private final GoodsRepository goodsRepository;

	/**
	 * 임의 데이터 생성
	 */
	@PostConstruct
	public void init() {
		// 회원정보
		MemberDto member1 = new MemberDto();
		member1.setLoginId("admin");
		member1.setPassword("admin1234");
		member1.setFirstName("관리자");
		member1.setLastName("테스트");
		member1.setBirthYear(2000);
		member1.setBirthMonth(1);
		member1.setBirthDay(2);
		memberRepository.save(member1);

		// 상품
		GoodsDto goods1 = new GoodsDto();
		goods1.setGoodsNm("사과");
		goods1.setUseYn("Y");
		goods1.setDesc("사과 descdesc");
		goodsRepository.save(goods1);
		GoodsDto goods2 = new GoodsDto();
		goods2.setGoodsNm("우유");
		goods2.setUseYn("Y");
		goodsRepository.save(goods2);
		GoodsDto goods3 = new GoodsDto();
		goods3.setGoodsNm("치즈");
		goods3.setUseYn("N");
		goodsRepository.save(goods3);
	}
}
