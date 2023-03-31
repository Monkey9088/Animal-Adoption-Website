package com.techelevator.dao;

import com.techelevator.model.Volunteer;

import java.util.List;

public interface VolunteerDao {

    List<Volunteer> findAll();

    Volunteer getVolunteerById(int volunteerId);

    Volunteer findByName(String name);

    int findIdByName(String name);

    int createVolunteer(Volunteer volunteer);

    void updateVolunteer(Volunteer volunteer);

    void deleteVolunteer(int volunteerId);

    int approveNewVolunteer(String username);

    boolean denyApplication(String username);
    boolean approveApplication(String username);

}





