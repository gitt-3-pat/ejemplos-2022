package com.ecommerce.market.webapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.market.webapp.dto.ProductMessage;
import com.ecommerce.market.webapp.exception.NotFoundException;
import com.ecommerce.market.webapp.model.ImageResource;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Order;
import com.ecommerce.market.webapp.model.OrderInfo;
import com.ecommerce.market.webapp.model.Item.ItemState;
import com.ecommerce.market.webapp.service.ItemService;
import com.ecommerce.market.webapp.service.OrderService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

@Controller
@RequestMapping("products")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderService orderService;

	private final Map<Long, ImageResource> resourceCache = new HashMap<>();

	@GetMapping(value = "{id}/image/{imageId}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImageResource(@PathVariable("id") String productId,
			@PathVariable("imageId") Long imageId) {

		final ImageResource image = resourceCache.get(imageId) != null ? resourceCache.get(imageId)
				: itemService.getImageResource(imageId);

		if (image == null) {
			throw new NotFoundException();
		} else {
			resourceCache.putIfAbsent(imageId, image);
			return image.getBytes();
		}
	}

	@GetMapping("{id}/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateGet(@PathVariable("id") Long id, Model model) {
		final Optional<Item> item = itemService.getItem(id);
		if (!item.isPresent()) {
			return "404";
		}
		model.addAttribute("item", item.get());
		model.addAttribute("types", Item.Type.values());
		model.addAttribute("itemStates", ItemState.values());
		return "update-product";
	}

	@PostMapping("update")
	public String update(Model model, @ModelAttribute Item item) {
		itemService.updateProduct(item);
		return "redirect:/products/" + item.getId();
	}

	@GetMapping("{id}")
	public String product(Model model, @PathVariable("id") Long id) {
		final Optional<Item> item = itemService.getItem(id);
		if (!item.isPresent()) {
			return "404";
		}
		model.addAttribute("item", item.get());
		return "product";
	}

	@PostMapping("{id}/contact")
	public ResponseEntity<String> contact(@PathVariable("id") Long id, @RequestBody ProductMessage message) {
		itemService.sendContactProduct(id, message);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> delete(@PathVariable("id") Long id, HttpServletRequest request) {
		itemService.delete(id, SecurityUtils.getCurrentUser());
		itemService.setWishlistForUser(request, SecurityUtils.getCurrentUser());
		return ResponseEntity.ok().build();
	}

	@PostMapping("{id}/accept")
	public ResponseEntity<String> accept(@PathVariable("id") Long id) {
		final Optional<Item> item = itemService.getItem(id);
		if (!item.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		itemService.acceptItem(item.get());
		return ResponseEntity.ok().build();
	}

	@PostMapping("{id}/decline")
	public ResponseEntity<String> decline(@PathVariable("id") Long id) {
		final Optional<Item> item = itemService.getItem(id);
		if (!item.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		itemService.declineItem(item.get());
		return ResponseEntity.ok().build();
	}

	@GetMapping("{id}/complete-shipping-details")
	public String completeShipping(@PathVariable("id") Long id, Model model) {
		final Optional<Item> item = itemService.getItem(id);
		if (!item.isPresent()) {
			return "404";
		}
		model.addAttribute("orderInfo", new OrderInfo());
		model.addAttribute("orderId", orderService.getOrderIdFromItemId(id));
		model.addAttribute("itemId", item.get().getId());
		return "shipping-details";

	}

	@PostMapping("/complete-shipping-details")
	public String completeShippingPost(@ModelAttribute OrderInfo orderInfo, @RequestParam("orderId") Long orderId,
			@RequestParam("itemId") Long itemId) {
		final Order order = orderService.getOrder(orderId);
		if (order == null || !order.getItems().stream().anyMatch(i -> i.getItemId().equals(itemId))) {
			return "404";
		}
		order.setOrderInfo(orderInfo);
		orderService.completeShippingDetails(order, itemId);

		return "redirect:/my-publications";

	}
}
