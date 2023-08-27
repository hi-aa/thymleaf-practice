package com.example.spring.login.dto;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
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

	@Range(min = 1900, max = 2023)
	private Integer birthYear;

	@Range(min = 1, max = 12)
	private Integer birthMonth;

	@Range(min = 1, max = 31)
	private Integer birthDay;

	@Value(value = "Y")
	@Pattern(regexp = "^[YN]$")
	private String testRadio;

	@Value(value = "Y")
	@Pattern(regexp = "^[YN]$")
	private String testCheck;

}
