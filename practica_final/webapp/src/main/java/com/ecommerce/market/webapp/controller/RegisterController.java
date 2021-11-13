package com.ecommerce.market.webapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ecommerce.market.webapp.dto.Provincia;
import com.ecommerce.market.webapp.exception.UserException;
import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.User.Provider;
import com.ecommerce.market.webapp.service.UserService;
import com.ecommerce.market.webapp.utils.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegisterController {

	@Autowired
	private FileUtils fileUtils;
	@Autowired
	private UserService userService;

	private static final String RESOURCE_PATH_CITIES = "cities/spain/spain.json";

	@Autowired
	private ObjectMapper mapper;

	@GetMapping("register")
	public String register(@RequestParam(required = false, name = "email") String email,
			@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "provider") Provider provider, Model model,
			HttpServletRequest request) {
		try {
			final List<Provincia> provincias = Arrays
					.asList(mapper.readValue(fileUtils.loadFromResources(RESOURCE_PATH_CITIES), Provincia[].class));
			model.addAttribute("provincias",
					provincias.stream().map(Provincia::getName).sorted().collect(Collectors.toList()));
		} catch (final JsonProcessingException e) {
			log.error("Error loading cities", e);
		}

		final User user = new User();
		user.setProvider(provider);
		user.setEmail(email);
		user.setFullName(name);

		final Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
			final String msg = (String) inputFlashMap.get("error");
			model.addAttribute("errorMsg", msg);
			model.addAttribute("user", inputFlashMap.get("user"));
		} else {
			model.addAttribute("user", user);
		}
		return "register";
	}

	@PostMapping("register")
	public String postRegister(@ModelAttribute("user") User user, @RequestParam("isVendor") Boolean isVendor,
			@RequestParam("isBuyer") Boolean isBuyer, RedirectAttributes ra) {
		// TO-DO SEND EMAIL
		try {
			userService.register(user, isVendor, isBuyer);
		} catch (final UserException e) {
			ra.addFlashAttribute("error", e.getMessage());
			ra.addFlashAttribute("user", user);
			return "redirect:/register";
		}
		ra.addFlashAttribute("infoMsg", "Usuario registrado correctamente");
		return "redirect:/login";
	}

}
