package com.malay.emr.dto;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private final String jwt;
    private final String name;

    public AuthResponse(String jwt, String name) {
        this.jwt = jwt;
        this.name = name;
    }

    public String getJwt() {
        return jwt;
    }

    public String getName() {
        return name;
    }
}
