package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pets")
public class PetController {

    private PetDao dao;

    public PetController(PetDao dao) {
        this.dao = dao;
    }

    @RequestMapping(path = "",method = RequestMethod.GET)
    public List<Pet> showAll() {
        return dao.listAllPets();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public Pet getPetById(@PathVariable int id) {
        return dao.getPetById(id);
    }


    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public int createPet(@RequestBody Pet pet){return dao.createPet(pet);}


    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/edit/{id}",method = RequestMethod.PUT)
    public void updateExistingPet(@PathVariable int id,@RequestBody Pet pet) {
        dao.updatePet(id,pet);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.GONE)
    @RequestMapping(path = "/edit/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable int id) {
        dao.deletePet(id);
    }

}
