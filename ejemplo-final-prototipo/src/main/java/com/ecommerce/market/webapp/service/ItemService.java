package com.ecommerce.market.webapp.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.market.webapp.dto.ProductMessage;
import com.ecommerce.market.webapp.model.ImageResource;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.Type;

public interface ItemService {

	List<Item> getAllItems(int page, int size, List<Type> types);

	ImageResource getImageResource(Long id);

	List<ImageResource> getImageResources(Long id);

	Optional<Item> getItem(Long id);

	void sendContactProduct(Long id, ProductMessage message);

	Item createItem(Item item, List<MultipartFile> resources);

	List<String> getAllBrandNames(List<Type> types);

	List<String> getAllItemSizesNames(List<Type> types);

	List<Item> getItemsByUser(String email);

	void delete(Long id, String user);

	void setWishlistForUser(HttpServletRequest request, String user);

	void updateProduct(Item item);

	void acceptItem(Item item);

	void declineItem(Item item);

	Double[] getPriceRanges();

	List<Item> processResults(List<String> brands, List<Integer> itemSizes, Double minPrice, Double maxPrice,
			List<Type> types);

}
