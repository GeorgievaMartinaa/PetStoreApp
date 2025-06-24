package com.example.petstoreapp.web.restController;

import com.example.petstoreapp.service.UserService;
import com.example.petstoreapp.web.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/user")
public class UserRestController {

    private final Random random = new Random();
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<List<UserDTO>> createRandomUsers(){
        List<UserDTO> userDTOs= new ArrayList<>();
        for(int i=0; i< random.nextInt(11); i++){
            userDTOs.add(userService.createRandomUser());
        }
        userService.saveUsers(userDTOs);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> usersDTO = userService.getAllUsers();

        return ResponseEntity.ok(usersDTO);
    }
}
