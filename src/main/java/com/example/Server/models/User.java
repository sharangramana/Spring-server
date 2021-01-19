package com.example.Server.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    //@NotBlank(message = "id is needed")

    @Min(value = 1, message = "Id cannot be left empty and it should be greater than 0")
    private int id;

    @NotNull(message = "Name of the user cannot be empty")
    private String name;

    private String gender;

    @NotNull(message = "Email address cannot be empty")
    private String email;

    private String phoneNumber;

    @NotNull(message = "Role must be provided")
    private String role;

    public User(int id, String name, String gender, String email, String phoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", role='" + role + '\'' +
                '}';
    }
}
