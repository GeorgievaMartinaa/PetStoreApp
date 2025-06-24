package com.example.petstoreapp.entity;


import com.example.petstoreapp.valueObjects.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;


@Entity
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private PetType type;
    private String description;
    private LocalDate birthDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;
    @ManyToOne
    private User owner;

    public Pet(String name, PetType type, String description, LocalDate birthDate, Money price, User owner) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.birthDate = birthDate;
        this.price = price;
        this.owner = owner;
    }

    public int getAge(){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
