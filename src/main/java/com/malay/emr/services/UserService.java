package com.malay.emr.services;

import com.malay.emr.dto.AddAppointmentDTO;
import com.malay.emr.dto.AppointmentDTo;
import com.malay.emr.dto.AuthRequest;
import com.malay.emr.dto.DoctorDetailsDTO;
import com.malay.emr.dto.Generic1;
import com.malay.emr.dto.Generic2;
import com.malay.emr.dto.PatientDetailsDTO;
import com.malay.emr.dto.PatientSearch;
import com.malay.emr.dto.VisitDataDTO;
import com.malay.emr.entities.AppointmentsEntity;
import com.malay.emr.entities.ComplaintsEntity;
import com.malay.emr.entities.CredentialsEntity;
import com.malay.emr.entities.DiagnosisEntity;
import com.malay.emr.entities.DoctorEntity;
import com.malay.emr.entities.MedicinesEntity;
import com.malay.emr.entities.PatientHistoryEntity;
import com.malay.emr.entities.PatientsEntity;
import com.malay.emr.entities.SavedTermsEntity;
import com.malay.emr.entities.TermTypesEntity;
import com.malay.emr.entities.TestsEntity;
import com.malay.emr.repository.AppointmentsDAO;
import com.malay.emr.repository.ComplaintsDAO;
import com.malay.emr.repository.CredentialsDAO;
import com.malay.emr.repository.DiagnosisDAO;
import com.malay.emr.repository.DoctorsDAO;
import com.malay.emr.repository.MedicinesDAO;
import com.malay.emr.repository.PatientHistoryDAO;
import com.malay.emr.repository.PatientsDAO;
import com.malay.emr.repository.SavedTermsDAO;
import com.malay.emr.repository.TermTypesDAO;
import com.malay.emr.repository.TestsDAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
    @Autowired
    AppointmentsDAO appointmentsDAO;
    @Autowired
    SavedTermsDAO savedTermsDAO;
    @Autowired
    PatientHistoryDAO patientHistoryDAO;
    @Autowired
    TermTypesDAO termTypesDAO;
    @Autowired
    ComplaintsDAO complaintsDAO;
    @Autowired
    TestsDAO testsDAO;
    @Autowired
    MedicinesDAO medicinesDAO;
    @Autowired
    DiagnosisDAO diagnosisDAO;
    
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


	public boolean addAppointment(AddAppointmentDTO dto, String email) {
		
		DoctorEntity doc = credentialsDAO.findByEmail(email).getDoctor();
		//check if conflict arises
		AppointmentsEntity app = appointmentsDAO.findByDoctorAndTime( doc, dto.getTime() );
		
		if(app == null) {
			AppointmentsEntity appointment = new AppointmentsEntity();
			appointment.setDoctor(doc);
			appointment.setPatient(patientsDAO.getById(dto.getPatientId()));
			appointment.setTime(dto.getTime());
			appointmentsDAO.save(appointment);
			return true;
		}
		
		return false;
	}


	public List<AppointmentDTo> getAllAppointmentsByDoctor(String email) {
		List<AppointmentsEntity> list = new ArrayList<>();
		list = credentialsDAO.findByEmail(email).getDoctor().getAppointments();
		List<AppointmentDTo> result = new ArrayList<>();
		for(AppointmentsEntity app:list) {
			PatientsEntity p = app.getPatient();
			result.add( new AppointmentDTo(app.getId(), app.getTime(), p.getGivenName()+" "+p.getLastName(),p.getSex() , p.getDob()) );
		}
		return result;
	}


	public boolean deleteAppointmentById(Integer id) {
		AppointmentsEntity appt = appointmentsDAO.getById(id);
		appointmentsDAO.delete(appt);
		
		return true;
	}

	
	@Transactional
	public boolean addVisitData(VisitDataDTO data) {
		
		PatientHistoryEntity visit = new PatientHistoryEntity();
		visit.setAdvice(data.getAdvice());
		visit.setBp1(data.getBp1());
		visit.setBp2(data.getBp2());
		visit.setHeight(data.getHeight());
		visit.setWeight(data.getWeight());
		visit.setPulse(data.getPulse());
		visit.setSpo2(data.getSpo2());
		visit.setPatient(patientsDAO.getById(data.getPatientId()));
		patientHistoryDAO.save(visit);
		
		//check if terms present or not
		TermTypesEntity comp = termTypesDAO.getById(2);
		for( Generic1 g: data.getComplaints() ) {
			SavedTermsEntity term = savedTermsDAO.findOneByTermAndType(g.getTerm(),comp);
			ComplaintsEntity ent = new ComplaintsEntity();
			if( term == null ) {
				//save the term
				SavedTermsEntity savedTerm = new SavedTermsEntity();
				savedTerm.setTerm(g.getTerm().toUpperCase());
				savedTerm.setType(comp);
				savedTermsDAO.save(savedTerm);
				ent.setTerm(savedTerm);
			}else {
				ent.setTerm(term);
				
			}
			ent.setHistory(visit);
			complaintsDAO.save(ent);
		}
		
		
		TermTypesEntity testType  = termTypesDAO.getById(4);
		for( Generic1 g: data.getTests() ) {
			SavedTermsEntity term = savedTermsDAO.findOneByTermAndType(g.getTerm(),testType);
			TestsEntity test = new TestsEntity(); 
			if( term == null ) {
				//save the term
				SavedTermsEntity savedTerm = new SavedTermsEntity();
				savedTerm.setTerm(g.getTerm().toUpperCase());
				savedTerm.setType(testType);
				savedTermsDAO.save(savedTerm);
				test.setTerm(savedTerm);
			}else {
				test.setTerm(term);
				
			}
			test.setHistory(visit);
			testsDAO.save(test);
		}
		
		TermTypesEntity med = termTypesDAO.getById(1);
		for( Generic2 g: data.getMedicines() ) {
			SavedTermsEntity term = savedTermsDAO.findOneByTermAndType(g.getTerm(),med);
			MedicinesEntity medicine = new MedicinesEntity();
			if( term == null ) {
				//save the term
				SavedTermsEntity savedTerm = new SavedTermsEntity();
				savedTerm.setTerm(g.getTerm().toUpperCase());
				savedTerm.setType(med);
				savedTermsDAO.save(savedTerm);
				medicine.setTerm(savedTerm);
			}else {
				medicine.setTerm(term);
				
			}
			medicine.setHistory(visit);
			medicine.setDuration(g.getDuration());
			medicine.setDurationType(g.getDurationType().toUpperCase());
			medicinesDAO.save(medicine);
		}
		
		TermTypesEntity diag = termTypesDAO.getById(3);
		for( Generic2 g: data.getDiagnosis() ) {
			SavedTermsEntity term = savedTermsDAO.findOneByTermAndType(g.getTerm(),diag);
			DiagnosisEntity diagn = new DiagnosisEntity();
			if( term == null ) {
				//save the term
				SavedTermsEntity savedTerm = new SavedTermsEntity();
				savedTerm.setTerm(g.getTerm().toUpperCase());
				savedTerm.setType(diag);
				savedTermsDAO.save(savedTerm);
				diagn.setTerm(savedTerm);
			}else {
				diagn.setTerm(term);
				
			}
			diagn.setHistory(visit);
			diagn.setDuration(g.getDuration());
			diagn.setDurationType(g.getDurationType().toUpperCase());
			diagnosisDAO.save(diagn);
		}
		
		return true;
	}
	
	
}
