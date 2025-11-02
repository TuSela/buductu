package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {
    @Size(min = 3, message = "Password is invalid" )
    private String password;
    private String firstName;
    private String lastName;
    private String dob;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

}
