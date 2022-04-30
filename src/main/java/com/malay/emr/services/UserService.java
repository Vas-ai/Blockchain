package com.malay.emr.services;

import com.malay.emr.dto.AuthRequest;
import com.malay.emr.dto.DoctorDetailsDTO;
import com.malay.emr.dto.PatientDetailsDTO;
import com.malay.emr.dto.PatientSearch;
import com.malay.emr.entities.CredentialsEntity;
import com.malay.emr.entities.DoctorEntity;
import com.malay.emr.entities.PatientsEntity;
import com.malay.emr.repository.CredentialsDAO;
import com.malay.emr.repository.DoctorsDAO;
import com.malay.emr.repository.PatientsDAO;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    CredentialsDAO credentialsDAO;
    @Autowired
    PatientsDAO patientsDAO;
    @Autowired
    DoctorsDAO doctorsDAO;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    ModelMapper mapper;
    
    Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);

    public int register(AuthRequest cred) {
        int id = 0;

        CredentialsEntity credentials = null;
        credentials = credentialsDAO.findByEmail(cred.getEmail());

        if( credentials == null ){
            credentials = new CredentialsEntity();
            credentials.setEmail(cred.getEmail());
            credentials.setPassword(encoder.encode(cred.getPassword()));
            
           
            credentialsDAO.save(credentials);
            id = credentials.getId();
        }
        return id;

    }
    

	public boolean addPatient(int id, PatientDetailsDTO details) {
		boolean result = false;
		try {
			//get credentials
			CredentialsEntity cred = credentialsDAO.getById(id);
			
			if(cred.getPatient() == null && cred.getDoctor() == null) {
				
				PatientsEntity patient = new PatientsEntity();
				patient.setCredentials( cred );
				patient.setGivenName(details.getGivenName());
				patient.setLastName(details.getLastName());
				patient.setMobile(details.getMobile());
				patient.setSex(details.getSex());
				patient.setDob(details.getDob());
				
				patientsDAO.save(patient);
				
				result = true;
				
			}
			
		}catch(Exception e) {
			
		}
		return result;
	}


	public boolean addDoctor(Integer credId, DoctorDetailsDTO details) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			//get credentials
			CredentialsEntity cred = credentialsDAO.getById(credId);
			if(cred.getDoctor() == null && cred.getPatient() == null) {
				
				DoctorEntity doctor = new DoctorEntity();
				doctor.setCredentials( cred );
				doctor.setFullName(details.getFullName());
				doctor.setClinic(details.getClinic());
				doctor.setQualification(details.getQualification());
				
				doctorsDAO.save(doctor);
				
				result = true;
				
			}
			
		}catch(Exception e) {
			
		}
		return result;
	}


	public List<PatientSearch> findPatientsByNameOrEmailOrContact(String term) {
		
		List<PatientsEntity> patients = new ArrayList<>();
		List<PatientSearch> list = new ArrayList<>();
		
		patients = patientsDAO.findByGivenNameOrLastNameOrMobile(term);
		logger.info(patients.size()+"");
		for(PatientsEntity p:patients) {
			
			PatientSearch dto = new PatientSearch();
			dto.setDob(p.getDob());
			dto.setGivenName(p.getGivenName());
			dto.setLastName(p.getLastName());
			dto.setMobile(p.getMobile());
			dto.setSex(p.getSex());
			dto.setId(p.getId());
			list.add(dto);
		}
		
		return list;
	}


	public boolean checkIfPatientProfileAdded(String email) {
		
		if(credentialsDAO.findByEmail(email).getPatient() == null )
		
		return false;
		
		else
			return true;
	}


	public String getTypeByEmail(String email) {
		CredentialsEntity cred = credentialsDAO.findByEmail(email);
		
		if(cred.getDoctor() == null && cred.getPatient()==null)
			return "none";
		else if( cred.getDoctor() == null && cred.getPatient()!=null )
			return "patient";
		else if(cred.getDoctor() != null && cred.getPatient()==null)
			return "doctor";
		
		return "";
	}
}
