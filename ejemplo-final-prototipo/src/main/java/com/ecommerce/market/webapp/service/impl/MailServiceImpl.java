package com.ecommerce.market.webapp.service.impl;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecommerce.market.webapp.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender emailSender;
	@Value("${spring.mail.from}")
	private String from;
	@Value("${spring.mail.fromName}")
	private String fromName;

	@Override
	public void sendMail(String to, String message, String subject, boolean isHtml) {
		final MimeMessage msg = emailSender.createMimeMessage();
		try {
			log.debug("Sending email message {} to {}", message, to);
			final MimeMessageHelper helper = new MimeMessageHelper(msg, false);
			helper.setFrom(new InternetAddress(from, fromName));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message, isHtml);
			emailSender.send(msg);
		} catch (final Exception e) {
			log.error("Error sending message {} to {}", message, to, e);
		}

	}

}
