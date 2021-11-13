package com.ecommerce.market.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.Type;
import com.ecommerce.market.webapp.service.ItemService;

@Controller
@RequestMapping("")
public class HomeController {

	private static final int MAX_PAGE = 10;

	@GetMapping("/")
	public String redirectHome() {
		return "redirect:/home";
	}

	@Autowired
	private ItemService itemService;

	@GetMapping("home")
	public String home(Model model, HttpServletRequest request) {
		final List<Item> list = itemService.getAllItems(0, MAX_PAGE, List.of(Type.values()));
		model.addAttribute("items", list);
		final Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null && inputFlashMap.get("message") != null) {
			final String msg = (String) inputFlashMap.get("message");
			model.addAttribute("message", msg);
		}
		return "index.html";
	}
}
