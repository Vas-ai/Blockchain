package com.malay.emr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.malay.emr.dto.AddAppointmentDTO;
import com.malay.emr.dto.AppointmentDTo;
import com.malay.emr.dto.DoctorDetailsDTO;
import com.malay.emr.dto.TermDTO;
import com.malay.emr.dto.UserEmailDTO;
import com.malay.emr.dto.VisitDataDTO;
import com.malay.emr.services.UserService;

@CrossOrigin
@Controller
public class DoctorsController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserEmailDTO dto;
	
	
	@RequestMapping(value = "/api/appointment", method = RequestMethod.POST)
    public ResponseEntity<String> addAppointments( @RequestBody AddAppointmentDTO appointment ) throws Exception{
    	
    	boolean added = userService.addAppointment(appointment,dto.getEmail());
    	String message = "{ \"added\": true }";
    	if(added==false)
    		message = "{ \"added\": false }";
    	
    	
    	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
    }
	
	@RequestMapping(value = "/api/appointment", method = RequestMethod.GET)
    public ResponseEntity< List<AppointmentDTo> > getAllAppointments(  ) throws Exception{
    	
    	List<AppointmentDTo> list = userService.getAllAppointmentsByDoctor(dto.getEmail());
    	
    	return new ResponseEntity< List<AppointmentDTo> >(list,new HttpHeaders(),HttpStatus.OK);
    }
	
	@RequestMapping(value="/api/appointment/{id}",method=RequestMethod.POST)
	public ResponseEntity<String> deleteAppointmentById(@PathVariable(name="id") Integer id) throws Exception {
		boolean deleted = userService.deleteAppointmentById(id);
		String message = "{ \"deleted\": true }";
    	if(deleted==false)
    		message = "{ \"deleted\": false }";
    	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/record",method=RequestMethod.POST)
	public ResponseEntity<String> addVisitData( @RequestBody VisitDataDTO data ) throws Exception {
		boolean added = userService.addVisitData(data);
		String message = "{ \"added\": true }";
    	if(added==false)
    		message = "{ \"added\": false }";
    	return new ResponseEntity<String>(message,new HttpHeaders(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/terms",method=RequestMethod.GET)
	public  ResponseEntity<List<TermDTO>> getTerms(@RequestParam("term") String term,@RequestParam("type") String type) throws Exception{
		
		return new ResponseEntity< List<TermDTO> >( userService.getTermsByType(term,type), new HttpHeaders(), HttpStatus.OK );
		
	}
	
	@RequestMapping(value="/api/history/{id}",method=RequestMethod.GET)
	public  ResponseEntity<List<VisitDataDTO>> getTerms(@PathVariable Integer id) throws Exception{
		
		return new ResponseEntity< List<VisitDataDTO> >( userService.getHistoryByPatientId(id), new HttpHeaders(), HttpStatus.OK );
		
	}
	
	@RequestMapping(value="/api/doctor",method=RequestMethod.GET)
	public  ResponseEntity<List<DoctorDetailsDTO>> getDoctors(@RequestParam("term") String term) throws Exception{
		
		return new ResponseEntity< List<DoctorDetailsDTO> >( userService.getDoctorsByTerm(term), new HttpHeaders(), HttpStatus.OK );
		
	}
	
}
