package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserUpdateRequest {
    @Size(min = 8, message = "PASSWORD_INVALID" )
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
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
