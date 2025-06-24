package com.example.petstoreapp.service.mapper;

import com.example.petstoreapp.entity.Cat;
import com.example.petstoreapp.entity.Dog;
import com.example.petstoreapp.entity.Pet;
import com.example.petstoreapp.entity.PetType;
import com.example.petstoreapp.entity.User;
import com.example.petstoreapp.exceptions.BadRequestException;
import com.example.petstoreapp.repository.UserRepository;
import com.example.petstoreapp.valueObjects.Money;
import com.example.petstoreapp.web.DTO.PetDTO;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    private final ConvertStringToEnum convertStringToEnum;
    private final UserRepository userRepository;

    public PetMapper(ConvertStringToEnum convertStringToEnum, UserRepository userRepository) {
        this.convertStringToEnum = convertStringToEnum;
        this.userRepository = userRepository;
    }

    public PetDTO toPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();

        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setDescription(pet.getDescription());
        petDTO.setType(pet.getType().name());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setPriceAmount(pet.getPrice().getAmount());
        petDTO.setPriceCurrency(pet.getPrice().getCurrency().name());
        petDTO.setOwnerId(getPetOwnerId(pet.getOwner()));
        petDTO.setAge(pet.getAge());

        if(pet instanceof Dog){
            petDTO.setDogRating(((Dog)pet).getRating());
        }

        return petDTO;
    }

    public Pet toEntity(PetDTO petDTO){
        Pet pet;

        if(petDTO.getType().equals(PetType.Cat.name()))
        {
            pet = new Cat();
        }else if(petDTO.getType().equals(PetType.Dog.name()))
        {
            Dog dog = new Dog();
            dog.setRating(petDTO.getDogRating() != null ? petDTO.getDogRating() : 0);

            pet=dog;
        }else {
            throw new BadRequestException("Invalid pet type");
        }
        pet.setName(petDTO.getName());
        pet.setType(convertStringToEnum.convertToPetType(petDTO.getType()));
        pet.setDescription(petDTO.getDescription());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setPrice(new Money(petDTO.getPriceAmount(), convertStringToEnum.convertToCurrency(petDTO.getPriceCurrency())));
        pet.setOwner(getPetOwner(petDTO.getOwnerId()));

        return pet;
    }

    private User getPetOwner(Integer ownerId){
        if(ownerId == null){
            return null;
        }else {
            return userRepository.findById(ownerId).orElseThrow(() -> new BadRequestException("User not found"));
        }
    }

    private Integer getPetOwnerId(User owner){
        if(owner != null){
            return owner.getId();
        }else {
            return null;
        }
    }
}
