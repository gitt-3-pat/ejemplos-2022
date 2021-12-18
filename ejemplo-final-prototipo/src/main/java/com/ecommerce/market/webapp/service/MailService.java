package com.ecommerce.market.webapp.service;

public interface MailService {

	void sendMail(String to, String message, String subject, boolean isHtml);

}
