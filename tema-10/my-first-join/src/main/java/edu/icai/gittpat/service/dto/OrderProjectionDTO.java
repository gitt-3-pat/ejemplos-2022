package edu.icai.gittpat.service.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProjectionDTO {

	private Long id;
	private String contactName;
	private String customerName;
	private String country;
	private LocalDateTime date;

}
