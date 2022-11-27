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

import com.malay.emr.dto.Generic2;
import com.malay.emr.dto.PatientSearch;
import com.malay.emr.dto.UserEmailDTO;
import com.malay.emr.dto.VisitDataDTO;
import com.malay.emr.services.UserService;

@CrossOrigin
@Controller
public class PatientController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserEmailDTO emailDto;

    @RequestMapping(value = "/api/patient", method = RequestMethod.GET)
    public ResponseEntity<List<PatientSearch>> searchPatients(@RequestParam(name="term") String term) throws Exception{
    	
    	ResponseEntity<List<PatientSearch>> response = null;
    	
    	List<PatientSearch> list = new ArrayList<>();
    	
    	list = userService.findPatientsByNameOrEmailOrContact(term);
    	
    	response = new ResponseEntity<List<PatientSearch>>(list,new HttpHeaders(),HttpStatus.OK);
    	
    	return response;
    }
    
    @RequestMapping(value = "/api/patient-profile", method = RequestMethod.GET)
    public ResponseEntity<String> checkPatientProfileAdded() throws Exception{
    	
    	boolean added = userService.checkIfPatientProfileAdded(emailDto.getEmail());
    	String message = "{ \"added\": true }";
    	if(added==false)
    		message = "{ \"added\": false }";
    	
    	
    	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/history/medicines-adjusted-history", method = RequestMethod.GET)
    public ResponseEntity<VisitDataDTO> getAdjustedMedicinesByPatient() throws Exception {
    	return new ResponseEntity<VisitDataDTO>(userService.getAdjustedMedicinesAndHistoryByPatient(emailDto.getEmail()),new HttpHeaders(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/api/history", method = RequestMethod.GET)
    public ResponseEntity<List<VisitDataDTO>> getHistoryByPatientEmail() throws Exception {
    	return new ResponseEntity<List<VisitDataDTO>>(userService.getHistoryByPatientEmail(emailDto.getEmail()),new HttpHeaders(), HttpStatus.OK);
    }
	
}
