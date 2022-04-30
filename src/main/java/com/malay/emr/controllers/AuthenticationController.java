package com.malay.emr.controllers;

import com.malay.emr.dto.AuthRequest;

import com.malay.emr.dto.AuthResponse;
import com.malay.emr.services.CredentialsService;
import com.malay.emr.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.malay.emr.security.JwtUtil;

@CrossOrigin
@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CredentialsService credentialsService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = credentialsService
                .loadUserByUsername(authenticationRequest.getEmail());

        String username= userDetails.getUsername();
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        final String type  = userService.getTypeByEmail( authenticationRequest.getEmail() );
        return ResponseEntity.ok(new AuthResponse(jwt,username,type));
    }


}