package com.ecommerce.market.webapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.market.webapp.dto.OrderDTO;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.service.ItemService;
import com.ecommerce.market.webapp.service.OrderService;
import com.ecommerce.market.webapp.service.UserService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

@Controller
public class ShoppingController {

	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderService orderService;

	@GetMapping("checkout/{id}")
	public String checkout(Model model, @PathVariable("id") Long productId) {
		final User user = userService.getUser(SecurityUtils.getCurrentUser());
		final Optional<Item> item = itemService.getItem(productId);
		if (item.isEmpty()) {
			return "404";
		}
		final OrderDTO order = new OrderDTO();
		order.setContactInfo(user.getContactInfo());
		order.setItem(item.get());
		order.getContactInfo().setName(user.getFullName());
		model.addAttribute("order", order);
		return "checkout";
	}

	@PostMapping("checkout")
	public String checkoutPost(@ModelAttribute OrderDTO order) {
		// TO-DO TPV and generate payment
		orderService.placeOrder(order);

		return "redirect:/my-buyouts";
	}

}
