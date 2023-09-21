package com.example.spring.goods.dto;

import lombok.Data;

@Data
public class GoodsDto {

	private long goodsId;

	private String goodsNm;

	/**
	 * 비고
	 */
	private String desc;

	/**
	 * 사용여부
	 */
	private String useYn;

	/**
	 * 상품 정렬순서
	 */
	private int goodsOdr;

}
