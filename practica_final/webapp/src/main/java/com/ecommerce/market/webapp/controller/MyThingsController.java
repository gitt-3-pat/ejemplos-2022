package com.ecommerce.market.webapp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ecommerce.market.webapp.exception.UserException;
import com.ecommerce.market.webapp.model.ImageResource;
import com.ecommerce.market.webapp.model.Order;
import com.ecommerce.market.webapp.model.OrderItem;
import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.service.ItemService;
import com.ecommerce.market.webapp.service.OrderService;
import com.ecommerce.market.webapp.service.UserService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyThingsController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;

	@GetMapping("/shopping-cart")
	public String shoppingCart() {
		return "shopping-cart";
	}

	@GetMapping("/my-buyouts")
	public String buyOuts(Model model) {
		final List<Order> orders = orderService.getOrdersForCurrentUser();
		model.addAttribute("buyouts", orders);
		final Map<Long, List<ImageResource>> images = orders.stream().map(Order::getItems).flatMap(x -> x.stream())
				.collect(Collectors.toMap(OrderItem::getItemId, oi -> itemService.getImageResources(oi.getItemId())));
		model.addAttribute("images", images);
		return "buyouts";
	}

	@GetMapping("/my-publications")
//	@Transactional
	public String publications(Model model) {
		model.addAttribute("publications",
				itemService.getItemsByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "publications";
	}

	@GetMapping("/my-sellings")
	public String sellings(Model model) {
		model.addAttribute("sellings",
				itemService.getItemsByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "sellings";
	}

	@GetMapping("/my-account")
	public String account(Model model, HttpServletRequest request) {
		model.addAttribute("user", userService.getUser(SecurityUtils.getCurrentUser()));
		final Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			final String msg = (String) inputFlashMap.get("infoMsg");
			model.addAttribute("infoMsg", msg);
		}
		return "my-account";
	}

	@PostMapping("my-account")
	public String modifyAccount(@ModelAttribute("user") User user,
			@RequestParam(value = "modifyPassword", defaultValue = "false") boolean newPassword,
			RedirectAttributes ra) {
		try {
			userService.update(user, newPassword);
		} catch (final UserException e) {
			log.error("Error modifying user data", e);
			return "redirect:/my-account";
		}
		ra.addFlashAttribute("infoMsg", "Se han modificado los datos correctamente");
		return "redirect:/my-account";
	}

}
