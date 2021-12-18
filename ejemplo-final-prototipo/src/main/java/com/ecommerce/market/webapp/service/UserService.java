package com.ecommerce.market.webapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.Wishlist;

public interface UserService {

	boolean register(User user, boolean isVendor, boolean isBuyer);

	void update(User user, boolean newPassword);

	Wishlist getWishlist(HttpServletRequest request);

	Wishlist addItemToWishlist(HttpServletRequest request, Long itemId);

	Wishlist removeItemFromWishlist(HttpServletRequest request, Long itemId);

	User getUser(String email);

	User create(User user);

	List<User> findAll();
}
