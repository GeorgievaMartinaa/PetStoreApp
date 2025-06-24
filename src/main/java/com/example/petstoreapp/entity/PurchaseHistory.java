package com.example.petstoreapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate executionDate;
    private int successfulBuyers;
    private int failedBuyers;

    public PurchaseHistory(LocalDate executionDate, int successfulBuyers, int failedBuyers) {
        this.executionDate = executionDate;
        this.successfulBuyers = successfulBuyers;
        this.failedBuyers = failedBuyers;
    }
}
