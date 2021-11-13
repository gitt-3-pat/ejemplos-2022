package com.ecommerce.market.webapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.market.webapp.model.Order;
import com.ecommerce.market.webapp.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/labels")
	public String orders(Model model) {
		model.addAttribute("orders", orderService.getOrdersToAssignLabels());
		return "orders-labels";
	}

	@PostMapping("/labels")
	public String assignLabel(@RequestParam("orderId") Long orderId, @RequestParam("label") MultipartFile label)
			throws IOException {
		final Order order = orderService.getOrder(orderId);
		if (order != null) {
			order.getOrderInfo().setShippingLabel(label.getBytes());
			order.getOrderInfo().setShippingMime(label.getOriginalFilename());
			orderService.updateOrder(order);
		}
		return "redirect:/orders/labels";
	}

	@GetMapping("/{id}/assign-label")
	public String assignLabelGet(@PathVariable("id") Long orderId, Model model) {
		final Order order = orderService.getOrder(orderId);
		model.addAttribute("order", order);
		return "assing-label";

	}

	@GetMapping("/{id}/label/download")
	public ResponseEntity<ByteArrayResource> downloadLabel(@PathVariable("id") Long orderId) {
		final Order order = orderService.getOrder(orderId);
		if (order == null || order.getOrderInfo().getShippingLabel() == null) {
			return ResponseEntity.notFound().build();
		}
		final ByteArrayResource resource = new ByteArrayResource(order.getOrderInfo().getShippingLabel());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=".concat(order.getOrderInfo().getShippingMime()))
				.contentLength(resource.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}
}
