package com.malay.emr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.malay.emr.dto.PatientSearch;
import com.malay.emr.services.UserService;

@CrossOrigin
@Controller
public class PatientController {
	
	@Autowired
	UserService userService;
	

    @RequestMapping(value = "/api/patient", method = RequestMethod.GET)
    public ResponseEntity<List<PatientSearch>> searchPatients(@RequestParam(name="term") String term) throws Exception{
    	
    	ResponseEntity<List<PatientSearch>> response = null;
    	
    	List<PatientSearch> list = new ArrayList<>();
    	
    	list = userService.findPatientsByNameOrEmailOrContact(term);
    	
    	response = new ResponseEntity<List<PatientSearch>>(list,new HttpHeaders(),HttpStatus.OK);
    	
    	return response;
    }
	
}
