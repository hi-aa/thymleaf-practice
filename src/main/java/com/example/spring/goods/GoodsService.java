package com.example.spring.goods;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring.goods.dto.GoodsDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsService {

	private final GoodsRepository goodsRepository;

	public List<GoodsDto> getAllGoods() {
		return goodsRepository.getAllGoods();
	}

	public GoodsDto getGoodsById(long goodsId) {
		return goodsRepository.getGoodsById(goodsId);
	}
}
