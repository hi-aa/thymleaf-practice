package com.example.spring.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {

	@GetMapping("/product")
	public String productListPage(Model model) {
		log.info("productListPage");
		return "product/productList";
	}
}
