package com.example.petstoreapp.service;

import com.example.petstoreapp.entity.User;
import com.example.petstoreapp.web.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createRandomUser();
    void saveUsers(List<UserDTO> users);
    List<UserDTO> getAllUsers();
}
