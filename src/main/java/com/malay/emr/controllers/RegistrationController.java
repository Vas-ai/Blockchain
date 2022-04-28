package com.malay.emr.controllers;

import com.malay.emr.dto.AuthRequest;
import com.malay.emr.dto.DoctorDetailsDTO;
import com.malay.emr.dto.PatientDetailsDTO;
import com.malay.emr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping(value="/api/register")
    public ResponseEntity<String> registeration(@RequestBody AuthRequest cred) throws Exception{
        ResponseEntity<String> response=new ResponseEntity<String>("",new HttpHeaders(), HttpStatus.OK);

        int id = userService.register(cred);

        if(id == 0) {
            response=new ResponseEntity<String>("{\"message\":\"already registered\"}",new HttpHeaders(),HttpStatus.CONFLICT);
        }
        else{
            response = new ResponseEntity<String>(" \"id\": "+id+" ",new HttpHeaders(), HttpStatus.OK);
        }

        return response;


    }
    
    @PostMapping(value = "/api/patient/{credId}")
    public ResponseEntity<?> addPatient(@PathVariable Integer credId,@RequestBody PatientDetailsDTO details) throws Exception{
		
    	boolean saved = userService.addPatient(credId,details);
    	ResponseEntity<String> response = new ResponseEntity<String>("",new HttpHeaders(),HttpStatus.BAD_REQUEST);
    	if(saved) {
    		response = new ResponseEntity<String>("{\"message\":\"created\"}",new HttpHeaders(),HttpStatus.OK);
    	}
    	
    	return response;
    	
    }
    
    @PostMapping(value = "/api/doctor/{credId}")
    public ResponseEntity<?> addDoctor(@PathVariable Integer credId,@RequestBody DoctorDetailsDTO details) throws Exception{
		
    	boolean saved = userService.addDoctor(credId,details);
    	ResponseEntity<String> response = new ResponseEntity<String>("",new HttpHeaders(),HttpStatus.BAD_REQUEST);
    	if(saved) {
    		response = new ResponseEntity<String>("{\"message\":\"created\"}",new HttpHeaders(),HttpStatus.OK);
    	}
    	
    	return response;
    	
    }

}
