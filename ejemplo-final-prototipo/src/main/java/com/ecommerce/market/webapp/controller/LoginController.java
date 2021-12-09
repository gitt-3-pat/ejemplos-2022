package com.ecommerce.market.webapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class LoginController {

	@GetMapping("login")
	public String login(@RequestParam(required = false, name = "error") String error, RedirectAttributes ra,
			HttpServletRequest request, Model model) {
		if (error != null) {
			ra.addFlashAttribute("error", error);
			return "redirect:/login";
		}
		final Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			final String msg = (String) inputFlashMap.get("error");
			model.addAttribute("errorMsg", msg);
		}
		return "login";
	}
}
