package com.example.demo.db.dto;

import java.util.Date;

public class UserDto {

    private long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    private Date registrationDate;

    public UserDto() {
    }

    public UserDto(long id, String name, String email, String password, boolean active, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
