package com.malay.emr.services;
import java.util.ArrayList;

import com.malay.emr.entities.CredentialsEntity;
import com.malay.emr.repository.CredentialsDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService implements UserDetailsService {
    @Autowired
    CredentialsDAO credentialsDAO;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        CredentialsEntity cred=credentialsDAO.findByEmail(email);

        User user=new User(cred.getEmail(),cred.getPassword(),new ArrayList<>());

        return user;
    }

}