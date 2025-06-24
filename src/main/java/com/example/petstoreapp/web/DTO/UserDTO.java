package com.example.petstoreapp.web.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal budgetAmount;
    private String budgetCurrency;
}
