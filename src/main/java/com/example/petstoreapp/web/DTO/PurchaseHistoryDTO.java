package com.example.petstoreapp.web.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PurchaseHistoryDTO {
    private int id;
    private LocalDate executionDate;
    private int successfulBuyers;
    private int failedBuyers;
}
