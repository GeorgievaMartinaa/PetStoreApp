package com.example.petstoreapp.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Dog extends Pet{
    private int rating;
}
