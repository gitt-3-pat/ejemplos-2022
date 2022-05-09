package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("DOCUMENTS")
public class Document {
    private @Column("DOC_ID") @Id
    Long DOC_ID;
    private @Column("DOC")
    String DOC;
    private @Column("USER_ID")
    Long USER_ID;
}
