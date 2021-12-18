package com.ecommerce.market.webapp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.market.webapp.model.ImageResource;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.ItemState;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

	@Query("SELECT * FROM item WHERE item.type in (:types)  AND item.item_state= :itemState")
	List<Item> findByTypeInAndItemState(List<String> types, @Param("itemState") ItemState itemState);

	@Query("SELECT * FROM item WHERE item.type in (:types)  AND item.item_state='AVAILABLE' AND (item.price BETWEEN :minPrice AND :maxPrice)")
	List<Item> findByTypeInAndAvailable(List<String> types, @Param("minPrice") Double minPrice,
			@Param("maxPrice") Double maxPrice);

	@Query("SELECT DISTINCT(item.brand) FROM item WHERE item.type in (:types) ")
	List<String> findDistinctBrandByType(@Param("types") List<String> types);

	@Query("SELECT DISTINCT(item.item_size) FROM item WHERE item.type in (:types) ")
	List<String> findDistinctItemSizeByType(@Param("types") List<String> types);

	// BRAND QUERIES//

	@Query("SELECT * FROM item WHERE item.type in (:types)  AND item.item_state='AVAILABLE' AND item.brand in (:brands) AND (item.price BETWEEN :minPrice AND :maxPrice)")
	List<Item> findByBrandIn(@Param("brands") List<String> brands, @Param("types") List<String> types,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("SELECT * FROM item WHERE item.type in (:types)  AND item.item_state='AVAILABLE' AND item.brand in (:brands) AND item.item_size in (:sizes)   AND (item.price BETWEEN :minPrice AND :maxPrice)")
	List<Item> findByBrandInAndSizeIn(@Param("brands") List<String> brands, @Param("sizes") List<Integer> sizes,
			@Param("types") List<String> types, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	// BRAND QUERIES//

	@Query("SELECT * FROM item WHERE item.type in (:types)  AND item.item_state='AVAILABLE' AND item.item_size in (:sizes)  AND (item.price BETWEEN :minPrice AND :maxPrice)")
	List<Item> findBySizeIn(@Param("sizes") List<Integer> sizes, @Param("types") List<String> types,
			@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

	@Query("SELECT * FROM item WHERE item.id IN (SELECT item_user.item_id FROM item_user WHERE item_user.user_email= :email)")
	List<Item> findByUser(@Param("email") String email);

	@Query("SELECT MIN(item.price) FROM item")
	double minPrice();

	@Query("SELECT MAX(item.price) FROM item")
	double maxPrice();

	@Query("SELECT * FROM image_resource where image_resource.id= :resourceId")
	ImageResource findByImageResourceId(@Param("resourceId") Long resourceId);

	@Query("SELECT * FROM image_resource where image_resource.item_id= :itemId")
	List<ImageResource> findImagesByItemId(@Param("itemId") Long itemId);

}
