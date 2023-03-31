package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.VolunteerDao;
import com.techelevator.model.ApproveVolunteerDTO;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.model.Volunteer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class VolunteerController {

    private VolunteerDao daoV;


    public VolunteerController(VolunteerDao daoV) {
        this.daoV = daoV;
    }

    @RequestMapping(value = "/volunteers", method = RequestMethod.GET)
    public List<Volunteer> findAll() {
        return daoV.findAll();
    }

    @RequestMapping(value = "/volunteers/{id}", method = RequestMethod.GET)
    public Volunteer getVolunteerById(@PathVariable int id) { return daoV.getVolunteerById(id);};


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/volunteer/apply", method = RequestMethod.POST)
    public int createVolunteer(@RequestBody Volunteer volunteer) {
        //User user= userDao.update()
        //(update user set role=ROLE_ADMIN where username =?)
        return daoV.createVolunteer(volunteer);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/volunteers", method = RequestMethod.PUT)
    public void updateVolunteer(@RequestBody Volunteer volunteer) {
        daoV.updateVolunteer(volunteer);
    };

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/volunteers/{id}", method = RequestMethod.DELETE)
    public void deleteVolunteer(@PathVariable int id) {daoV.deleteVolunteer(id);};

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public void approveVolunteer(@Valid @RequestBody ApproveVolunteerDTO person) {
        try {
            daoV.approveNewVolunteer(person.getUsername());
            daoV.approveApplication(person.getUsername());
            throw new UserAlreadyExistsException();
        } catch (UserAlreadyExistsException derp) {
            System.out.println("The username must be unique.");
        }
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/admin", method = RequestMethod.PUT)
    public void deny(@RequestBody ApproveVolunteerDTO person) {
           daoV.denyApplication(person.getUsername());

    };
}






