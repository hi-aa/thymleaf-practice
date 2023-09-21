package com.example.spring.member.dto;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class MemberDto {

	private long id;

	@NotBlank
	private String loginId;

	@NotBlank
	private String password;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotNull
	@Range(min = 1900, max = 2023)
	private Integer birthYear;

	@NotNull
	@Range(min = 1, max = 12)
	private Integer birthMonth;

	@NotNull
	@Range(min = 1, max = 31)
	private Integer birthDay;

	@Value(value = "Y")
	@Pattern(regexp = "^[YN]$")
	private String testRadio;

	@Value(value = "Y")
	@Pattern(regexp = "^[YN]$")
	private String testCheck;

	public MemberDto() {
	}

	public MemberDto(MemberUpdateDto updateMember) {
		this.loginId = updateMember.getLoginId();
		this.password = updateMember.getPassword();
		this.firstName = updateMember.getFirstName();
		this.lastName = updateMember.getLastName();
		this.birthYear = updateMember.getBirthYear();
		this.birthMonth = updateMember.getBirthMonth();
		this.birthDay = updateMember.getBirthDay();
		this.testRadio = updateMember.getTestRadio();
		this.testCheck = updateMember.getTestCheck();
	}

}
