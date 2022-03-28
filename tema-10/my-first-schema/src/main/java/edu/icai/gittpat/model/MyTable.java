package edu.icai.gittpat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("MY_TABLE")
public class MyTable {
	private @Column("ID") @Id Long id;
	private @Column("FIELD1") String field1;
	private @Column("FIELD2") BigDecimal field2;
	private @Column("MY_DATE") LocalDate date;
	private @Column("MY_TIME") LocalTime time;
	private @Column("FLAG") Boolean flag;
}

