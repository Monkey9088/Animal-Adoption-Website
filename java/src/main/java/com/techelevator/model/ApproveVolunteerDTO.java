package com.techelevator.model;

public class ApproveVolunteerDTO {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "ApproveVolunteerDTO{" +
                "username='" + username +'\'' +
                '}';
    }
}
