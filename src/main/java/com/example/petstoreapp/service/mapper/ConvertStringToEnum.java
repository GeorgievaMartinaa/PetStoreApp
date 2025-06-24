package com.example.petstoreapp.service.mapper;

import com.example.petstoreapp.entity.PetType;
import com.example.petstoreapp.exceptions.BadRequestException;
import com.example.petstoreapp.valueObjects.Currency;
import org.springframework.stereotype.Component;

@Component
public class ConvertStringToEnum {

    public Currency convertToCurrency(String value) throws BadRequestException {
        try {
            return Currency.valueOf(value);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadRequestException("Invalid currency value: " + value);
        }
    }

    public PetType convertToPetType(String value) throws BadRequestException{
        try {
            return PetType.valueOf(value);
        }catch (IllegalArgumentException | NullPointerException e){
            throw new BadRequestException("Invalid pet type value: " + value);
        }
    }
}
