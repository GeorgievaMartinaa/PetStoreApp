package com.example.petstoreapp.graphql;

import com.example.petstoreapp.service.PetService;
import com.example.petstoreapp.service.PurchaseHistoryService;
import com.example.petstoreapp.service.UserService;
import com.example.petstoreapp.web.DTO.PetDTO;
import com.example.petstoreapp.web.DTO.PurchaseHistoryDTO;
import com.example.petstoreapp.web.DTO.UserDTO;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {
    private final UserService userService;
    private final PetService petService;
    private final PurchaseHistoryService historyService;

    public QueryResolver(UserService userService, PetService petService, PurchaseHistoryService historyService) {
        this.userService = userService;
        this.petService = petService;
        this.historyService = historyService;
    }

    @QueryMapping
    public List<UserDTO> allUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public List<PetDTO> allPets() {
        return petService.getAllPets();
    }

    @QueryMapping
    public List<PurchaseHistoryDTO> purchaseHistoryLogs(){
        return historyService.getAllHistoryLogs();
    }

}
