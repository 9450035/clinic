package org.clinic.service.dto;

public class TokenDTO {
    private String token;

    public TokenDTO(String jwt) {
        this.token = jwt;
    }

    public TokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
