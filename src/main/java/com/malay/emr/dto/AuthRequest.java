package com.malay.emr.dto;

public class AuthRequest {
    private String email;
    private String password;
    private String type;


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

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    //need default constructor for JSON Parsing
    public AuthRequest()
    {

    }

    public AuthRequest(String username, String password, String type) {
        this.setEmail(email);
        this.setPassword(password);
        this.setType(type);
    }

}