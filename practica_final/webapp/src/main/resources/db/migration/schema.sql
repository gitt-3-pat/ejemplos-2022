SET NAMES utf8mb4 ;

SET character_set_client = utf8mb4 ;
CREATE TABLE IF NOT EXISTS `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `internal_rating` int(11) NOT NULL,
  `item_size` int(11) DEFAULT NULL,
  `item_state` varchar(255) DEFAULT NULL,
  `item_year` int(11) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

SET character_set_client = utf8mb4 ;
CREATE TABLE IF NOT EXISTS `image_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bytes` longblob,
  `file_name` varchar(255) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IMAGE_R_ID_FK` (`item_id`),
  CONSTRAINT `IMAGE_R_ID_FK`
      FOREIGN KEY (`item_id`)
      REFERENCES `item` (`id`)
);


SET character_set_client = utf8mb4 ;
CREATE TABLE  IF NOT EXISTS `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additional_information` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `localty` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `delivery_reference` varchar(255) DEFAULT NULL,
  `delivery_status` varchar(255) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `lenght` int(11) DEFAULT NULL,
  `shipping_label` longblob,
  `shipping_mime` varchar(255) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `order_state` varchar(255) DEFAULT NULL,
  `payed` bit(1) NOT NULL,
  `payed_at` datetime NOT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



SET character_set_client = utf8mb4 ;
CREATE TABLE  IF NOT EXISTS `user` (
  `additional_information` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `localty` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
);




SET character_set_client = utf8mb4 ;
CREATE TABLE  IF NOT EXISTS `order_user` (
  `user_email` varchar(255) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_email`, `order_id`),
  CONSTRAINT `USER_O_ID_FK`
     FOREIGN KEY (`user_email`)
     REFERENCES `user` (`email`),
  CONSTRAINT `ORDER_U_ID_FK`
     FOREIGN KEY (`order_id`)
     REFERENCES `orders` (`id`)
);


SET character_set_client = utf8mb4 ;
CREATE TABLE IF NOT EXISTS `order_item` (
  `item_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`item_id`, `order_id`),
  CONSTRAINT `ITEM_O_ID_FK`
     FOREIGN KEY (`item_id`)
     REFERENCES `item` (`id`),
  CONSTRAINT `ORDER_I_ID_FK`
     FOREIGN KEY (`order_id`)
     REFERENCES `orders` (`id`)
);


SET character_set_client = utf8mb4 ;
CREATE TABLE  IF NOT EXISTS `item_user` (
  `user_email` varchar(255) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_email`, `item_id`),
  CONSTRAINT `USER_I_ID_FK`
     FOREIGN KEY (`user_email`)
     REFERENCES `user` (`email`),
  CONSTRAINT `ITEM_U_ID_FK`
     FOREIGN KEY (`item_id`)
     REFERENCES `item` (`id`)
);
