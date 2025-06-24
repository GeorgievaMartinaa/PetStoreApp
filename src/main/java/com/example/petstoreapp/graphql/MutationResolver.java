package com.example.petstoreapp.graphql;

import com.example.petstoreapp.service.PetService;
import com.example.petstoreapp.service.UserService;
import com.example.petstoreapp.web.DTO.PetDTO;
import com.example.petstoreapp.web.DTO.UserDTO;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MutationResolver {
    private final UserService userService;
    private final PetService petService;
    private final Random random = new Random();


    public MutationResolver(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
    }

    @MutationMapping
    public List<UserDTO> createRandomUsers(){
        List<UserDTO> users = new ArrayList<>();

        for(int i=0; i< random.nextInt(11); i++){
            users.add(userService.createRandomUser());
        }

        userService.saveUsers(users);

        return users;
    }

    @MutationMapping
    public List<PetDTO> createRandomPets(){
        List<PetDTO> pets = new ArrayList<>();

        for(int i=0; i< random.nextInt(21); i++){
            pets.add(petService.createRandomPet());
        }

        petService.saveAllPets(pets);

        return pets;
    }

    @MutationMapping
    public String buyPetForAllUsers(){
        petService.buyPetsForAllUsersAndLogHistory();

        return "Buying process is finished!";
    }
}
