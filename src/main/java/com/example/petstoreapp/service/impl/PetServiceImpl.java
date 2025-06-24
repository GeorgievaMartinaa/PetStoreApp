package com.example.petstoreapp.service.impl;

import com.example.petstoreapp.entity.Cat;
import com.example.petstoreapp.entity.Dog;
import com.example.petstoreapp.entity.Pet;
import com.example.petstoreapp.entity.PetType;
import com.example.petstoreapp.entity.PurchaseHistory;
import com.example.petstoreapp.entity.User;
import com.example.petstoreapp.repository.PetRepository;
import com.example.petstoreapp.repository.PurchaseHistoryRepository;
import com.example.petstoreapp.repository.UserRepository;
import com.example.petstoreapp.service.PetService;
import com.example.petstoreapp.service.mapper.PetMapper;
import com.example.petstoreapp.valueObjects.Currency;
import com.example.petstoreapp.valueObjects.Money;
import com.example.petstoreapp.web.DTO.PetDTO;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final Faker faker = new Faker();

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;
    private final PurchaseHistoryRepository historyRepository;

    public PetServiceImpl(PetRepository petRepository, UserRepository userRepository, PetMapper petMapper, PurchaseHistoryRepository historyRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.petMapper = petMapper;
        this.historyRepository = historyRepository;
    }

        @Override
    public PetDTO createRandomPet() {
        PetType type= faker.options().option(PetType.class);
        PetDTO petDTO = new PetDTO();

        if(type.equals(PetType.Cat))
        {
            //create cat
            petDTO.setName(faker.cat().name());
            petDTO.setDescription(faker.cat().breed());
            petDTO.setType(PetType.Cat.name());
            petDTO.setBirthDate(faker.date().birthday(1,20)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }else {
            // create dog
            petDTO.setName(faker.dog().name());
            petDTO.setDescription(faker.dog().breed());
            petDTO.setType(PetType.Dog.name());
            petDTO.setBirthDate(faker.date().birthday(1,25)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            petDTO.setDogRating(faker.number().numberBetween(1,10));
        }

        petDTO.setAge(calculatePetAge(petDTO));
        petDTO.setPriceAmount(calculatePetPrice(petDTO).getAmount());
        petDTO.setPriceCurrency(Currency.USD.name());

        return petDTO;
    }

    @Override
    @Transactional
    public void saveAllPets(List<PetDTO> petDTOs) {
        List<Pet> pets = petDTOs.stream().map(petMapper::toEntity).collect(Collectors.toList());
        petRepository.saveAll(pets);
    }

    @Override
    public List<PetDTO> getAllPets() {
        List<Pet> pets =petRepository.findAll();

        List<PetDTO> petDTOs = pets.stream().map(petMapper::toPetDTO).collect(Collectors.toList());

        return petDTOs;
    }

    @Override
    @Transactional
    public void buyPetsForAllUsersAndLogHistory() {
        List<User> allUsers = userRepository.findAll();
        int successfulBuyers = 0;
        int failedBuyers =0;

        for (User user:allUsers) {
            Pet pet = petRepository.findFirstByOwnerIsNullAndPriceAmountLessThan(user.getBudget().getAmount());
            if (pet != null){
                pet.setOwner(user);
                // subtract budget
                decreaseUserBudget(user, pet.getPrice());
                petRepository.save(pet);
                successfulBuyers++;
                if(pet instanceof Cat){
                    System.out.println("Meow, cat " + pet.getName()  + " has owner " + user.getFirstName() + " " + user.getLastName());
                }else if( pet instanceof Dog){
                    System.out.println("Woof, dog " + pet.getName()  + " has owner " + user.getFirstName() + " " + user.getLastName());
                }
            }else {
                failedBuyers++;
                System.out.println("Sorry, no pet can be bought!!");
            }

        }
        historyRepository.save(new PurchaseHistory(LocalDate.now(), successfulBuyers, failedBuyers));
    }

    private void decreaseUserBudget(User user, Money petPrice){
        Money newBudget = user.getBudget().subtract(petPrice);

        user.setBudget(newBudget);
        userRepository.save(user);

    }

    private Money calculatePetPrice(PetDTO petDTO){
        BigDecimal amount= BigDecimal.ZERO;

        if(petDTO.getType().equals(PetType.Cat.name()))
        {
            amount = BigDecimal.valueOf(petDTO.getAge());
        }else if(petDTO.getType().equals(PetType.Dog.name())){
            amount =  BigDecimal.valueOf(petDTO.getAge() + petDTO.getDogRating());
        }


        return new Money(amount, Currency.USD);
    }

    private Integer calculatePetAge(PetDTO petDTO){
        return Period.between(petDTO.getBirthDate(), LocalDate.now()).getYears();
    }
}
