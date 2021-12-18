package com.ecommerce.market.webapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecommerce.market.webapp.exception.UserException;
import com.ecommerce.market.webapp.model.ContactInfo;
import com.ecommerce.market.webapp.model.Item;
import com.ecommerce.market.webapp.model.Item.ItemState;
import com.ecommerce.market.webapp.model.User;
import com.ecommerce.market.webapp.model.User.Provider;
import com.ecommerce.market.webapp.model.Wishlist;
import com.ecommerce.market.webapp.repository.ItemRepository;
import com.ecommerce.market.webapp.repository.UserRepository;
import com.ecommerce.market.webapp.service.MailService;
import com.ecommerce.market.webapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private JdbcAggregateTemplate template;

	@Value("${spring.mail.from}")
	private String from;

	@Lazy
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean register(User user, boolean isVendor, boolean isBuyer) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserException("Ya existe una cuenta registrada con ese correo");
		}
		final StringBuilder builder = new StringBuilder();
		builder.append("<p>Nueva alta de usuario");
		if (isBuyer & isVendor) {
			builder.append(" vendedor y comprador.</p>");
		} else if (isBuyer) {
			builder.append(" comprador.</p>");
		} else if (isVendor) {
			builder.append(" vendedor.</p>");
		}
		builder.append("<br/>");
		builder.append("<p>Los datos del usuario son los siguientes:</p>");
		builder.append("<ul>");
		builder.append("<li><b>Nombre:</b> " + user.getFullName() + "</li>");
		builder.append("<li><b>E-mail:</b> " + user.getEmail() + "</li>");
		builder.append("<li><b>DNI/NIF:</b> " + user.getContactInfo().getDni() + "</li>");
		builder.append("<li><b>País:</b> " + user.getContactInfo().getCountry() + "</li>");
		builder.append("</ul>");
		mailService.sendMail(from, builder.toString(), "Nueva alta", true);
		if (user.getProvider() == null) {
			user.setProvider(Provider.LOCAL);
		}
		if (StringUtils.hasText(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		template.insert(user);
//		userRepository.save(user);
		return true;
	}

	@Override
	public void update(User user, boolean newPassword) {
		final User userDb = userRepository.findByEmail(user.getEmail());
		if (!user.getEmail().equals(userDb.getEmail())) {
			userDb.setEmail(user.getEmail());
		}
		if (!user.getFullName().equals(userDb.getFullName())) {
			userDb.setFullName(user.getFullName());
		}
		if (newPassword) {
			userDb.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if (user.getContactInfo() == null) {
			user.setContactInfo(new ContactInfo());
		}
		if (userDb.getContactInfo() == null) {
			userDb.setContactInfo(new ContactInfo());
		}
		if (!user.getContactInfo().getAddress().equals(userDb.getContactInfo().getAddress())) {
			userDb.getContactInfo().setAddress(user.getContactInfo().getAddress());
		}
		if (!user.getContactInfo().getCountry().equals(userDb.getContactInfo().getCountry())) {
			userDb.getContactInfo().setCountry(user.getContactInfo().getCountry());
		}
		if (!user.getContactInfo().getDni().equals(userDb.getContactInfo().getDni())) {
			userDb.getContactInfo().setDni(user.getContactInfo().getDni());
		}
		if (!user.getContactInfo().getLocalty().equals(userDb.getContactInfo().getLocalty())) {
			userDb.getContactInfo().setLocalty(user.getContactInfo().getLocalty());
		}
		if (!user.getContactInfo().getPhone().equals(userDb.getContactInfo().getPhone())) {
			userDb.getContactInfo().setPhone(user.getContactInfo().getPhone());
		}
		if (!user.getContactInfo().getPostalCode().equals(userDb.getContactInfo().getPostalCode())) {
			userDb.getContactInfo().setPostalCode(user.getContactInfo().getPostalCode());
		}
		if (!user.getContactInfo().getProvince().equals(userDb.getContactInfo().getProvince())) {
			userDb.getContactInfo().setProvince(user.getContactInfo().getProvince());
		}
		userRepository.save(userDb);
	}

	@Override
	public Wishlist getWishlist(HttpServletRequest request) {
		if (request.getSession().getAttribute("wishlist") != null) {
			return (Wishlist) request.getSession().getAttribute("wishlist");
		} else {
			final Wishlist list = new Wishlist();
			request.getSession().setAttribute("wishlist", list);
			return list;
		}

	}

	@Override
	public Wishlist addItemToWishlist(HttpServletRequest request, Long itemId) {
		final Wishlist list = getWishlist(request);

		final Optional<Item> item = itemRepository.findById(itemId);
		if (item.isPresent() && ItemState.AVAILABLE.equals(item.get().getItemState())) {
			list.getItems().add(item.get());
		}
		return list;

	}

	@Override
	public Wishlist removeItemFromWishlist(HttpServletRequest request, Long itemId) {
		final Wishlist list = this.getWishlist(request);
		list.getItems().removeIf(i -> i.getId().equals(itemId));
		return list;

	}

	@Override
	public User getUser(String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public User create(User user) {
		return template.insert(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

}
