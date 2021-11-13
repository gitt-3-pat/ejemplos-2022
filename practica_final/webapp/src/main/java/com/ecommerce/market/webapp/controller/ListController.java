package com.ecommerce.market.webapp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.Type;
import com.ecommerce.market.webapp.service.ItemService;

@Controller
public class ListController {

	@Autowired
	private ItemService itemService;

	private static final String MIN_PRICE = "0";
	private static final String MAX_PRICE = "40000";

	@GetMapping("/clothes")
	private String clothes(Model model, @RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,
			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "cuts", required = false) List<String> cuts,
			@RequestParam(name = "sleeves", required = false) List<String> sleeves,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "minPrice", required = false, defaultValue = MIN_PRICE) Double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = MAX_PRICE) Double maxPrice,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {

		final List<Item> list = itemService.processResults(brands, itemSizes, minPrice, maxPrice,
				List.of(Type.VESTIDO, Type.PANTALON, Type.CAMISETA));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		model.addAttribute("dresses", true);

		model.addAttribute("breadCrumb", "Toda la ropa");
		addFiltersToModel(model, List.of(Type.VESTIDO, Type.PANTALON, Type.CAMISETA));
		model.addAttribute("personStats", true);

		return "list";

	}

	@GetMapping("/clothes/dresses")
	private String dresses(Model model, @RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "cuts", required = false) List<String> cuts,
			@RequestParam(name = "sleeves", required = false) List<String> sleeves,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "minPrice", required = false, defaultValue = MIN_PRICE) Double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = MAX_PRICE) Double maxPrice,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {

		final List<Item> list = itemService.processResults(brands, itemSizes, minPrice, maxPrice,
				List.of(Type.VESTIDO));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		model.addAttribute("dresses", true);

		model.addAttribute("breadCrumb", "Todos los vestidos");
		addFiltersToModel(model, List.of(Type.VESTIDO));
		model.addAttribute("personStats", true);

		return "list";

	}

	@GetMapping("/clothes/pants")
	private String pants(Model model, @RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "cuts", required = false) List<String> cuts,
			@RequestParam(name = "sleeves", required = false) List<String> sleeves,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "minPrice", required = false, defaultValue = MIN_PRICE) Double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = MAX_PRICE) Double maxPrice,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, minPrice, maxPrice,
				List.of(Type.PANTALON));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		model.addAttribute("dresses", true);

		model.addAttribute("breadCrumb", "Todos los pantalones");
		model.addAttribute("personStats", false);
		addFiltersToModel(model, List.of(Type.PANTALON));

		return "list";

	}

	@GetMapping("/clothes/shirts")
	private String shirts(Model model, @RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "cuts", required = false) List<String> cuts,
			@RequestParam(name = "sleeves", required = false) List<String> sleeves,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "minPrice", required = false, defaultValue = MIN_PRICE) Double minPrice,
			@RequestParam(name = "maxPrice", required = false, defaultValue = MAX_PRICE) Double maxPrice,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, minPrice, maxPrice,
				List.of(Type.CAMISETA));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);

		model.addAttribute("breadCrumb", "Todas las camisetas");
		addFiltersToModel(model, List.of(Type.CAMISETA));
		return "list";

	}

	@GetMapping("/accessories")
	private String accessories(Model model,
			@RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, Double.valueOf(MIN_PRICE),
				Double.valueOf(MAX_PRICE), List.of(Type.ARCHIVO_DIGITAL, Type.ABRIGO, Type.ZAPATO, Type.MISCELANEA));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		addFiltersToModel(model, List.of(Type.ARCHIVO_DIGITAL, Type.ABRIGO, Type.ZAPATO, Type.MISCELANEA));
		model.addAttribute("breadCrumb", "Todos los accesorios");
		return "list";

	}

	@GetMapping("/accessories/digital-files")
	private String veilsAndHeaddresses(Model model,
			@RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, Double.valueOf(MIN_PRICE),
				Double.valueOf(MAX_PRICE), List.of(Type.ARCHIVO_DIGITAL));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);

		addFiltersToModel(model, List.of(Type.ARCHIVO_DIGITAL));

		model.addAttribute("breadCrumb", "Ficheros digitales");

		return "list";

	}

	@GetMapping("/accessories/shoes")
	private String shoes(Model model, @RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, Double.valueOf(MIN_PRICE),
				Double.valueOf(MAX_PRICE), List.of(Type.ZAPATO));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		addFiltersToModel(model, List.of(Type.ZAPATO));
		model.addAttribute("breadCrumb", "Todos los zapatos");

		return "list";

	}

	@GetMapping("/accessories/miscellaneous")
	private String miscellaneous(Model model,
			@RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,

			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, Double.valueOf(MIN_PRICE),
				Double.valueOf(MAX_PRICE), List.of(Type.MISCELANEA));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		addFiltersToModel(model, List.of(Type.MISCELANEA));
		model.addAttribute("breadCrumb", "Misceláneos");
		return "list";

	}

	@GetMapping("/accessories/coats")
	private String coatsAndCapes(Model model,
			@RequestParam(required = false, name = "page", defaultValue = "0") Integer page,
			@RequestParam(required = false, name = "size", defaultValue = "6") Integer size,
			@RequestParam(name = "brands", required = false) List<String> brands,
			@RequestParam(name = "sizes", required = false) List<Integer> itemSizes,
			@RequestParam(name = "orderBy", required = false, defaultValue = "price-asc") String orderBy) {
		final List<Item> list = itemService.processResults(brands, itemSizes, Double.valueOf(MIN_PRICE),
				Double.valueOf(MAX_PRICE), List.of(Type.ABRIGO));
		shortByOrder(orderBy, list);
		model.addAttribute("items", list);
		addFiltersToModel(model, List.of(Type.ABRIGO));
		model.addAttribute("breadCrumb", "Todos los abrigos");

		return "list";

	}

	private void addFiltersToModel(Model model, List<Type> types) {
		model.addAttribute("brands", itemService.getAllBrandNames(types));
		model.addAttribute("itemSizes", itemService.getAllItemSizesNames(types));
		final Double[] princeRanges = itemService.getPriceRanges();
		model.addAttribute("minPrice", princeRanges[0].intValue());
		model.addAttribute("maxPrice", princeRanges[1].intValue());
	}

	private void shortByOrder(String orderBy, List<Item> items) {
		switch (orderBy.toLowerCase()) {
		case "price-desc":
			Collections.sort(items, (o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
			break;
		case "price-asc":
		default:
			Collections.sort(items, (o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
			break;
		}
	}

}
