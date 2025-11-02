package com.devteria.identity_service.dto.response;

public class AuthenticationResponse {
    private boolean authenticated;
    public String token;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
