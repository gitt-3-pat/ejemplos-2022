package com.ecommerce.market.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.market.webapp.model.Wishlist;
import com.ecommerce.market.webapp.service.UserService;

@Controller
@RequestMapping("wishlist")
public class WishlistController {

	@Autowired
	private UserService userService;

	@GetMapping
	private String wishlist() {

		return "wishlist";
	}

	@GetMapping("refresh")
	private String wishlistRefresh() {

		return "wishlist :: #wishlist-body";
	}

	@PostMapping("/{id}/add")
	public String addToWishlist(@PathVariable("id") Long id, HttpServletRequest request,
			@RequestBody(required = false) String verify) {
		final Wishlist list = userService.addItemToWishlist(request, id);
		if (list != null) {
			request.getSession().setAttribute("wishlist", list);
		}
		if (verify != null) {
			return "fragments/header :: #wishlist-menu ";
		} else {
			return "redirect:/home";
		}
	}

	@PostMapping("/{id}/remove")
	public String removeItem(@PathVariable("id") Long id, HttpServletRequest request) {
		final Wishlist list = userService.removeItemFromWishlist(request, id);
		if (list != null) {
			request.getSession().setAttribute("wishlist", list);
		}
		return "fragments/header :: #wishlist-menu ";
	}
}
