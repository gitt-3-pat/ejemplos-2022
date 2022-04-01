package edu.icai.gittpat.service.dto;

public record CustomerDTO(
		Long id,
		String customerName,
		String contactName,
		String country) {}
