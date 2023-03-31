package com.techelevator.dao;

import com.techelevator.model.Pet;
import com.techelevator.model.PetNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pet> listAllPets() {
        List<Pet> allPets = new ArrayList<>();

        //sql
        String sql =    "SELECT * " +
                        "FROM pets;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            allPets.add(mapRowToPet(results));
        }

        return allPets;
    }

    @Override
    public Pet getPetById(int id) {
        Pet pet = null;
        //sql
        String sql =    "SELECT * " +
                        "FROM pets " +
                        "Where id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            pet = mapRowToPet(result);
        } else {
            throw new PetNotFoundException();
        }
        return pet;
    }

    @Override
    public int createPet(Pet pet){

        String sql = "INSERT INTO pets(gender, pet_name, animal_type, color, age, image_link, is_available, spayed_neutered, tagline) " +
                "VALUES(?,?,?,?,?,?,?,?,?) RETURNING id;";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, pet.getGender(), pet.getName(), pet.getType(), pet.getColor(), pet.getAge(),
                pet.getImage(), pet.isAvailable(), pet.isFixed(), pet.getTagline());

        return newId;
    }

    @Override
    public boolean updatePet(int id, Pet pet) {
        String sql =    "UPDATE pets " +
                        "SET gender = ?, pet_name = ?, animal_type = ?, color = ?, " +
                        "age = ?, image_link = ?, is_available = ?, spayed_neutered = ?, tagline = ? " +
                        "WHERE id = ?";
        return jdbcTemplate.update(sql, pet.getGender(), pet.getName(), pet.getType(), pet.getColor(), pet.getAge(),
                            pet.getImage(), pet.isAvailable(), pet.isFixed(), pet.getTagline(), id) == 1;
    }

    @Override
    public void deletePet(int id) {
        String sql = "DELETE FROM pets " +
                "WHERE id= ?;";
        jdbcTemplate.update(sql, id);
    }


    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setId(rs.getInt("id"));
        pet.setGender(rs.getString("gender"));
        pet.setName(rs.getString("pet_name"));
        pet.setType(rs.getString("animal_type"));
        pet.setColor(rs.getString("color"));
        pet.setAge(rs.getDouble("age"));
        pet.setImage(rs.getString("image_link"));
        pet.setAvailable(rs.getBoolean("is_available"));
        pet.setFixed(rs.getBoolean("spayed_neutered"));
        pet.setTagline(rs.getString("tagline"));

        return pet;

    }






}
