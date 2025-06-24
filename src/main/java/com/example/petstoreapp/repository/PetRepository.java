package com.example.petstoreapp.repository;

import com.example.petstoreapp.entity.Pet;
import com.example.petstoreapp.valueObjects.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    Pet findFirstByOwnerIsNullAndPriceAmountLessThan(BigDecimal amount);
    List<Pet> findAllByOwnerIsNull();
    List<Pet> findAllByPriceLessThan(Money money);
    List<Pet> findAllByPriceAmountLessThan(BigDecimal amount);
}
