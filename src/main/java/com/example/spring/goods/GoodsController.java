package com.example.spring.goods;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 상품 관련 작업
 * @author : hi-aa
 * @date   : 2023-09-21
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class GoodsController {

	private final GoodsService goodsService;

	@GetMapping("/goods")
	public String productListPage(Model model) {
		model.addAttribute("goodsList", goodsService.getAllGoods());
		return "goods/goodsList";
	}
}
