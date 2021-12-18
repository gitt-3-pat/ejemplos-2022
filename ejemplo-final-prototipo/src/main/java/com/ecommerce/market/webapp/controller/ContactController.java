package com.ecommerce.market.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.market.webapp.dto.ProductMessage;
import com.ecommerce.market.webapp.service.MailService;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private MailService mailService;

	@Value("${spring.mail.from}")
	private String infoMail;

	@GetMapping
	public String contact() {
		return "contact";
	}

	@PostMapping
	public ResponseEntity<String> contactPOST(@RequestBody ProductMessage message) {
		final StringBuilder body = new StringBuilder();
		body.append("<p>Nueva mensaje recibido</p>");
		body.append("<p>Nombre: <b>" + message.getUsername() + "</b></p>");
		body.append("<p>Correo: <b>" + message.getEmail() + "</b></p>");
		body.append("<p>Mensaje: <b>" + message.getMessage() + "</b></p>");
		mailService.sendMail(infoMail, body.toString(), "Nuevo mensaje de contacto recibido ", true);
		return ResponseEntity.ok().build();
	}
}
