package com.ecommerce.market.webapp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.market.webapp.dto.ProductMessage;
import com.ecommerce.market.webapp.model.ImageResource;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.ItemState;
import com.ecommerce.market.webapp.model.Item.Type;
import com.ecommerce.market.webapp.model.Order;
import com.ecommerce.market.webapp.model.Order.OrderState;
import com.ecommerce.market.webapp.model.Wishlist;
import com.ecommerce.market.webapp.repository.ItemRepository;
import com.ecommerce.market.webapp.repository.OrderRepository;
import com.ecommerce.market.webapp.service.ItemService;
import com.ecommerce.market.webapp.service.MailService;
import com.ecommerce.market.webapp.service.UserService;
import com.ecommerce.market.webapp.utils.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;;

	@Value("${spring.mail.from}")
	private String infoMail;

	@Override
	public List<Item> getAllItems(int page, int size, List<Type> types) {
		return itemRepository.findByTypeInAndItemState(types.stream().map(Type::name).collect(Collectors.toList()),
				ItemState.AVAILABLE);
	}

	@Override
	public ImageResource getImageResource(Long id) {
		return itemRepository.findByImageResourceId(id);
	}

	@Override
	public Optional<Item> getItem(Long id) {
		return itemRepository.findById(id);
	}

	@Override
	public void sendContactProduct(Long id, ProductMessage message) {
		final StringBuilder body = new StringBuilder();
		body.append("<p>Nueva petición de contacto sobre el producto " + id + ".</p>");
		body.append("<p>Nombre: <b>" + message.getUsername() + "</b></p>");
		body.append("<p>Correo: <b>" + message.getEmail() + "</b></p>");
		body.append("<p>Mensaje: <b>" + message.getMessage() + "</b></p>");
		mailService.sendMail(infoMail, body.toString(), "Consulta al vendedor del producto " + id, true);

	}

	@Override
	public Item createItem(Item item, List<MultipartFile> resources) {
		if (!resources.isEmpty()) {
			resources.forEach(r -> {
				try {
					if (r.getSize() < 20000000) {
						final ImageResource resource = new ImageResource();
						resource.setBytes(r.getBytes());
						resource.setFileName(r.getName());
						item.getImages().add(resource);
					}
				} catch (final Exception e) {
					log.error("Error adding resource {}", r.getName());
				}

			});
		}
		// TO-DO validations, no dejarlo como approved
		item.setUser(userService.getUser(SecurityUtils.getCurrentUser()));
		item.setItemState(ItemState.AVAILABLE);
		item.setPrice(item.getPrice() + 0.15 * item.getPrice());

		return itemRepository.save(item);
	}

	@Override
	public List<String> getAllBrandNames(List<Type> types) {
		return itemRepository.findDistinctBrandByType(types.stream().map(Type::name).collect(Collectors.toList()));
	}

	@Override
	public List<String> getAllItemSizesNames(List<Type> types) {
		return itemRepository.findDistinctItemSizeByType(types.stream().map(Type::name).collect(Collectors.toList()));
	}

	@Override
	public List<Item> processResults(List<String> brands, List<Integer> itemSizes, Double minPrice, Double maxPrice,
			List<Type> types) {
		final Double[] priceRange = { minPrice, maxPrice };

		return processResults(brands, itemSizes, priceRange, types);
	}

	private List<Item> processResults(List<String> brands, List<Integer> itemSizes, Double[] priceRange,
			List<Type> types) {
		final boolean hasBrands = brands != null;
		final boolean hasSizes = itemSizes != null;
		if (!hasBrands && hasSizes) {
			return itemRepository.findBySizeIn(itemSizes, types.stream().map(Type::name).collect(Collectors.toList()),
					priceRange[0], priceRange[1]);

		} else if (hasBrands && !hasSizes) {
			return itemRepository.findByBrandIn(brands, types.stream().map(Type::name).collect(Collectors.toList()),
					priceRange[0], priceRange[1]);
		} else if (hasBrands && hasSizes) {
			return itemRepository.findByBrandInAndSizeIn(brands, itemSizes,
					types.stream().map(Type::name).collect(Collectors.toList()), priceRange[0], priceRange[1]);
		} else {
			return itemRepository.findByTypeInAndAvailable(types.stream().map(Type::name).collect(Collectors.toList()),
					priceRange[0], priceRange[1]);
		}

	}

	@Override
	public List<Item> getItemsByUser(String email) {
		return itemRepository.findByUser(email);
	}

	@Override
	@Transactional
	public void delete(Long id, String user) {
		final Item item = itemRepository.findById(id).orElse(null);
		if (item != null && item.getItemState().equals(ItemState.AVAILABLE)) {
			// TO-DO check permissions
			orderRepository.deleteItemFromOrderByItemId(id);
			itemRepository.deleteById(id);

		}
	}

	@Override
	public void setWishlistForUser(HttpServletRequest request, String user) {
		final Wishlist list = new Wishlist();
		request.getSession().setAttribute("wishlist", list);

	}

	@Override
	public void updateProduct(Item item) {
		final Item itemDB = itemRepository.findById(item.getId()).orElse(null);
		if (itemDB != null) {
			if (!item.getBrand().equals(itemDB.getBrand())) {
				itemDB.setBrand(item.getBrand());
			}

			if (item.getDescription() != null && !item.getDescription().equals(itemDB.getDescription())) {
				itemDB.setDescription(item.getDescription());
			}
			if (item.getPrice() != itemDB.getPrice()) {
				itemDB.setPrice(item.getPrice());
			}

			if (item.getInternalRating() != itemDB.getInternalRating()) {
				itemDB.setInternalRating(item.getInternalRating());
			}
			if (!item.getItemState().equals(itemDB.getItemState())) {
				itemDB.setItemState(item.getItemState());
			}
			if (!item.getType().equals(itemDB.getType())) {
				itemDB.setType(item.getType());
			}
			if (item.getTitle() != null && !item.getTitle().equals(itemDB.getTitle())) {
				itemDB.setTitle(item.getTitle());
			}
			if (item.getMaterial() != null && !item.getMaterial().equals(itemDB.getMaterial())) {
				itemDB.setMaterial(item.getMaterial().equals("") ? null : item.getMaterial());
			}
			if (!item.getItemSize().equals(itemDB.getItemSize())) {
				itemDB.setItemSize(item.getItemSize());
			}
			itemRepository.save(itemDB);
		}
	}

	@Override
	public void acceptItem(Item item) {
		item.setItemState(ItemState.PRE_CONFIRMED);
		itemRepository.save(item);
	}

	@Override
	public void declineItem(Item item) {
		item.setItemState(ItemState.CANCELADO);
		final Order order = orderRepository.findByItemId(item.getId());
		order.setOrderState(OrderState.CANCELADO);
		itemRepository.save(item);
		orderRepository.save(order);

	}

	@Override
	public Double[] getPriceRanges() {
		try {
			return new Double[] { itemRepository.minPrice(), itemRepository.maxPrice() };
		} catch (final Exception e) {
			log.warn("No items on database yet");
			return new Double[] { Double.valueOf(0), Double.valueOf(2000) };
		}
	}

	@Override
	public List<ImageResource> getImageResources(Long id) {
		return itemRepository.findImagesByItemId(id);
	}

}
