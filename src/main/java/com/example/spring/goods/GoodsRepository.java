package com.example.spring.goods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.spring.goods.dto.GoodsDto;

@Repository
public class GoodsRepository {

	private static Map<Long, GoodsDto> store = new HashMap<>();
	private static long sequence = 0L;
	private static int order = 0;

	/**
	 * 목록 조회
	 * @author : hi-aa
	 * @date   : 2023-09-21
	 * @return
	 */
	public List<GoodsDto> getAllGoods() {
		List<GoodsDto> list = new ArrayList<>(store.values())
				.stream()
				.filter(v -> "Y".equals(v.getUseYn()))
				.sorted(Comparator.comparing(GoodsDto::getGoodsOdr))
				.toList();
		return list;
	}

	// TODO : 단일 조회

	/**
	 * 저장
	 * @author : hi-aa
	 * @date   : 2023-09-21
	 * @param saveGoods
	 * @return
	 */
	public GoodsDto save(GoodsDto saveGoods) {
		saveGoods.setGoodsId(++sequence);
		saveGoods.setGoodsOdr(++order);
		store.put(saveGoods.getGoodsId(), saveGoods);
		return saveGoods;
	}

	// TODO : 수정
	// TODO : 삭제

}
