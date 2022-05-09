package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocument {
    private Long USER_ID;
    private String USER;
    private Long DOC_ID;
    private String DOC;
}
