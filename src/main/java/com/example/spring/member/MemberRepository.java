package com.example.spring.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.spring.member.dto.MemberDto;
import com.example.spring.member.dto.MemberUpdateDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	/**
	 * update member
	 * @param id
	 * @param member
	 * @return
	 */
	public MemberDto updateMember(long id, MemberUpdateDto updateMember) throws IllegalArgumentException {
		if(this.getMemberById(id) == null) {
			throw new IllegalArgumentException("멤버 정보가 존재하지 않습니다.");
		}

		MemberDto member = new MemberDto(updateMember);
		member.setId(id);
		store.put(id, member);
		return member;
	}
}
