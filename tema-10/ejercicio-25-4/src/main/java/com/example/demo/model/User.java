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
@Table("USERS")
public class User {
    private @Column("USER_ID") @Id
    Long USER_ID;
    private @Column("USER")
    String USER;
    private @Column("COMMENT")
    String COMMENT;
}
