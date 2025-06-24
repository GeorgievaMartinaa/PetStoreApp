package com.example.petstoreapp.web.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetDTO {
    private int id;
    private String name;
    private String type;
    private String description;
    private LocalDate birthDate;
    private BigDecimal priceAmount;
    private String priceCurrency;
    private Integer ownerId;
    private Integer dogRating;
    private int age;
}
