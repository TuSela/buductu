package com.devteria.identity_service.dto.response;

public class AuthenticationResponse {
    private boolean authenticated;
    private String token;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
