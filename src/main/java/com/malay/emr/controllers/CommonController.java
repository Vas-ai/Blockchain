package com.malay.emr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.malay.emr.dto.AccessDTO;
import com.malay.emr.dto.ApprovalDTO;
import com.malay.emr.dto.UserEmailDTO;
import com.malay.emr.services.UserService;

@CrossOrigin
@Controller
public class CommonController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserEmailDTO dto;
	
	 @RequestMapping(value = "/api/approval", method = RequestMethod.POST)
	    public ResponseEntity<String> addHistoryApproval( @RequestBody ApprovalDTO dto) throws Exception{
	    	
	    	boolean added = userService.addHistoryApproval(dto);
	    	String message = "{ \"added\": true }";
	    	if(added==false)
	    		message = "{ \"added\": false }";
	    	
	    	
	    	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/api/approval", method = RequestMethod.GET)
	    public ResponseEntity<List<AccessDTO>> addHistoryApproval( ) throws Exception{
	    	
	    	return new ResponseEntity<List<AccessDTO>>(userService.getPermissionsByPatientEmail(dto.getEmail()),new HttpHeaders(),HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/api/approval/toggle/{id}", method = RequestMethod.GET)
	    public ResponseEntity<String> toggleApproval(@PathVariable int id ) throws Exception{
		 	String message  = "{ \"changed\": true }";
		 	userService.togglePermissionsById(id);
	    	
		 	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
	    }
}
