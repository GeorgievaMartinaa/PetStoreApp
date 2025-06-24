package com.example.petstoreapp.service;

import com.example.petstoreapp.entity.Pet;
import com.example.petstoreapp.web.DTO.PetDTO;

import java.util.List;


public interface PetService {
    PetDTO createRandomPet();
    void saveAllPets(List<PetDTO> pet);
    List<PetDTO> getAllPets();
    void buyPetsForAllUsersAndLogHistory();
}
