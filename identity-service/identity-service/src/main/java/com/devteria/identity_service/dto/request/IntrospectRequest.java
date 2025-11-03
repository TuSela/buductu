package com.devteria.identity_service.dto.request;

public class IntrospectRequest {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
