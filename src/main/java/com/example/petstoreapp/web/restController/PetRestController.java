package com.example.petstoreapp.web.restController;

import com.example.petstoreapp.service.PetService;
import com.example.petstoreapp.web.DTO.PetDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/pet")
public class PetRestController {

    private final PetService petService;
    private final Random random = new Random();

    public PetRestController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<PetDTO>> createRandomPets(){
        List<PetDTO> petDTOs = new ArrayList<>();

        for(int i=0; i<random.nextInt(21); i++){
            petDTOs.add(petService.createRandomPet());
        }

        petService.saveAllPets(petDTOs);

        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PetDTO>> getAllPets(){
        List<PetDTO> petDTOs = petService.getAllPets();


        return ResponseEntity.ok(petDTOs);
    }

    @PostMapping("/buy")
    public ResponseEntity<Void> buyPetForAllUsers(){
        petService.buyPetsForAllUsersAndLogHistory();
        return ResponseEntity.ok().build();
    }
}
