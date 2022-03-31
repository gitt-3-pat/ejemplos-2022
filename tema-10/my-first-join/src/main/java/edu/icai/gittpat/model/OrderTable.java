package edu.icai.gittpat.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("ORDERS")
public class OrderTable {
	private @Column("ID") @Id Long id;
	private @Column("CUSTOMER_ID") Long customerId;
	private @Column("ORDER_DATE") LocalDate date;

}