package com.example.spring.login.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

	private static Map<Long, MemberDto> store = new HashMap<>();
	private static long sequence = 0L;

	/**
	 * member 저장
	 * @param member
	 * @return
	 */
	public MemberDto save(MemberDto member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	/**
	 * member 조회 (id)
	 * @param id
	 * @return
	 */
	public MemberDto getMemberById(long id) {
		return store.get(id);
	}

	/**
	 * member 조회 (loginId)
	 * @param loginId
	 * @return
	 */
	public Optional<MemberDto> getMemberByLoginId(String loginId) {
		return this.getAllMembers().stream().filter(v -> v.getLoginId().equals(loginId))
				.findFirst();
	}

	/**
	 * all member list
	 * @return
	 */
	public List<MemberDto> getAllMembers() {
		return new ArrayList<>(store.values());
	}
}
