package com.example.spring.goods;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.goods.dto.GoodsDto;

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

	/**
	 * 목록 페이지
	 * @author : hi-aa
	 * @date   : 2023-09-26
	 * @param model
	 * @return
	 */
	@GetMapping("/goods")
	public String goodsListPage(Model model) {
		model.addAttribute("goodsList", goodsService.getAllGoods());
		return "goods/goodsList";
	}

	/**
	 * 상세 페이지
	 * @author : hi-aa
	 * @date   : 2023-09-26
	 * @param model
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/goods/{goodsId}")
	public String goodsPage(Model model, @PathVariable long goodsId) {
		GoodsDto goods = goodsService.getGoodsById(goodsId);

		if(goods == null || goods.getGoodsId() == 0) {
			log.info("Invalid ID={}", goodsId);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID");
		}
		model.addAttribute("goods", goods);
		return "goods/goodsDetail";
	}

	@PostMapping("/goods/save")
	public String goodsSave(@ModelAttribute GoodsDto goodsDto) {
		// TODO : save

		return "redirect:/goods";
	}
}
