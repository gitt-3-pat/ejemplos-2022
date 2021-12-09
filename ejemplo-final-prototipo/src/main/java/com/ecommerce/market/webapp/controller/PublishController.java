package com.ecommerce.market.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.service.ItemService;

@Controller
@RequestMapping("publish")
public class PublishController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public String publication(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("types", Item.Type.values());
		return "publish";
	}

	@PostMapping
	public String post(@RequestParam("files") List<MultipartFile> files, @ModelAttribute Item item,
			RedirectAttributes ra) {
		itemService.createItem(item, files);
		ra.addFlashAttribute("message", "Su anuncio ha sido publicado correctamente, ¡Gracias!");
		return "redirect:/home";
	}

}
