package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {

    List<Pet> listAllPets();

    Pet getPetById(int id);

    int createPet(Pet pet);

    boolean updatePet(int id, Pet pet);

    void deletePet(int id);




}
