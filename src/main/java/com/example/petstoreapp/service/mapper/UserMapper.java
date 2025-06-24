package com.example.petstoreapp.service.mapper;

import com.example.petstoreapp.entity.User;
import com.example.petstoreapp.exceptions.BadRequestException;
import com.example.petstoreapp.valueObjects.Money;
import com.example.petstoreapp.web.DTO.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ConvertStringToEnum convertStringToEnum;

    public UserMapper(ConvertStringToEnum convertStringToEnum) {
        this.convertStringToEnum = convertStringToEnum;
    }

    public UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBudgetAmount(user.getBudget().getAmount());
        userDTO.setBudgetCurrency(user.getBudget().getCurrency().name());

        return userDTO;
    }

    public User toUser (UserDTO userDTO) throws BadRequestException {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setBudget(new Money(userDTO.getBudgetAmount(), convertStringToEnum.convertToCurrency(userDTO.getBudgetCurrency())));

        return user;
    }

}
