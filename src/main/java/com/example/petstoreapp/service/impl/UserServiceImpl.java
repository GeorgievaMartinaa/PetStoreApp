package com.example.petstoreapp.service.impl;

import com.example.petstoreapp.entity.User;
import com.example.petstoreapp.exceptions.BadRequestException;
import com.example.petstoreapp.repository.UserRepository;
import com.example.petstoreapp.service.UserService;
import com.example.petstoreapp.service.mapper.UserMapper;
import com.example.petstoreapp.valueObjects.Currency;
import com.example.petstoreapp.valueObjects.Money;
import com.example.petstoreapp.web.DTO.UserDTO;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Faker faker = new Faker();
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO createRandomUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(faker.name().firstName());
        userDTO.setLastName(faker.name().lastName());
        userDTO.setEmail(faker.internet().emailAddress());
        userDTO.setBudgetAmount(BigDecimal.valueOf(faker.number().numberBetween(5,50)));
        userDTO.setBudgetCurrency(Currency.USD.name());

        return userDTO;
    }

    @Override
    @Transactional
    public void saveUsers(List<UserDTO> userDTOs) {
        List<User>users = userDTOs.stream().map(userMapper::toUser).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users= userRepository.findAll();

        return users.stream().map(userMapper::toUserDTO).collect(Collectors.toList());
    }
}
