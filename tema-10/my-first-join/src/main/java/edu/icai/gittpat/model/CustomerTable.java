package edu.icai.gittpat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("CUSTOMERS")
public class CustomerTable {
	private @Column("ID") @Id Long id;
	private @Column("CUSTOMER_NAME") String customerName;
	private @Column("CONTACT_NAME") String contactName;
	private @Column("COUNTRY") String country;

}