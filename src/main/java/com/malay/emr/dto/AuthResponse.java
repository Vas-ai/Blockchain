package com.malay.emr.dto;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private final String jwt;
    private final String name;
    private final String type;
    
    public AuthResponse(String jwt, String name,String type) {
        this.jwt = jwt;
        this.name = name;
        this.type = type;
    }

    public String getJwt() {
        return jwt;
    }

    public String getName() {
        return name;
    }

	public String getType() {
		return type;
	}
    
    
    
    
}
