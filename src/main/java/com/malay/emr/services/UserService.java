package com.malay.emr.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.malay.emr.dto.AccessDTO;
import com.malay.emr.dto.AddAppointmentDTO;
import com.malay.emr.dto.AppointmentDTo;
import com.malay.emr.dto.ApprovalDTO;
import com.malay.emr.dto.AuthRequest;
import com.malay.emr.dto.DoctorDetailsDTO;
import com.malay.emr.dto.Generic1;
import com.malay.emr.dto.Generic2;
import com.malay.emr.dto.PatientDetailsDTO;
import com.malay.emr.dto.PatientSearch;
import com.malay.emr.dto.TermDTO;
import com.malay.emr.dto.VisitDataDTO;
import com.malay.emr.entities.AppointmentsEntity;
import com.malay.emr.entities.ComplaintsEntity;
import com.malay.emr.entities.CredentialsEntity;
import com.malay.emr.entities.DiagnosisEntity;
import com.malay.emr.entities.DoctorEntity;
import com.malay.emr.entities.HistoryPermissionEntity;
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
import com.malay.emr.repository.HistoryApprovalDAO;
import com.malay.emr.repository.MedicinesDAO;
import com.malay.emr.repository.PatientHistoryDAO;
import com.malay.emr.repository.PatientsDAO;
import com.malay.emr.repository.SavedTermsDAO;
import com.malay.emr.repository.TermTypesDAO;
import com.malay.emr.repository.TestsDAO;

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
    @Autowired
    HistoryApprovalDAO historyApprovalDAO;
    
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
			result.add( new AppointmentDTo(app.getId(), app.getTime(), p.getGivenName()+" "+p.getLastName(),p.getSex() , p.getDob(),p.getId()) );
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


	public List<TermDTO> getTermsByType(String term,String type) {
		TermTypesEntity typeEntity = termTypesDAO.findFirstByType(type);
		List<SavedTermsEntity> terms = savedTermsDAO.findByTermContainingAndType(term, typeEntity, PageRequest.of(0, 10));
		List<TermDTO> list = new ArrayList<>();
		for(SavedTermsEntity t:terms) {
			TermDTO dto = new TermDTO();
			dto.setWord(t.getTerm());
			dto.setId(t.getId());
			list.add(dto);
			logger.info(t.getTerm());
		}
		logger.info(terms.size()+"");
		return list; 
	}


	public List<VisitDataDTO> getHistoryByPatientId(Integer id) {
		
		List<VisitDataDTO> list = new ArrayList<>();
		PatientsEntity patient = patientsDAO.getById(id);
		List<PatientHistoryEntity> history = patientHistoryDAO.findByPatientOrderByIdDesc(patient);
		for(PatientHistoryEntity h:history) {
			
			VisitDataDTO dto = new VisitDataDTO();
			dto.setAdvice(h.getAdvice());
			dto.setBp1(h.getBp1());
			dto.setBp2(h.getBp2());
			dto.setGivenName(patient.getGivenName());
			dto.setLastName(patient.getLastName());
			dto.setHeight(h.getHeight());
			dto.setPulse(h.getPulse());
			dto.setPatientId(patient.getId());
			dto.setSpo2(h.getSpo2());
			dto.setTemp(h.getTemp());
			dto.setWeight(h.getWeight());
			dto.setDate(h.getDate());
			dto.setId(h.getId());
			List<Generic1> complaints = new ArrayList<Generic1>();
			for( ComplaintsEntity comp: h.getComplaints() ) {
				Generic1 g = new Generic1();
				g.setId(comp.getId());
				g.setTerm(comp.getTerm().getTerm());
				complaints.add(g);
			}
			
			List<Generic1> tests = new ArrayList<Generic1>();
			for( TestsEntity test: h.getTests() ) {
				Generic1 g = new Generic1();
				g.setId(test.getId());
				g.setTerm(test.getTerm().getTerm());
				tests.add(g);
			}
			
			List<Generic2> diagnosis = new ArrayList<Generic2>();
			for( DiagnosisEntity diag: h.getDiagnosis() ) {
				Generic2 g = new Generic2();
				g.setId(diag.getId());
				g.setTerm(diag.getTerm().getTerm());
				g.setDuration(diag.getDuration());
				g.setDurationType(diag.getDurationType());
				diagnosis.add(g);
			}
			
			List<Generic2> medicines = new ArrayList<Generic2>();
			for( MedicinesEntity med: h.getMedicines() ) {
				Generic2 g = new Generic2();
				g.setId(med.getId());
				g.setTerm(med.getTerm().getTerm());
				g.setDuration(med.getDuration());
				g.setDurationType(med.getDurationType());
				medicines.add(g);
			}
			dto.setComplaints(complaints);
			dto.setTests(tests);
			dto.setDiagnosis(diagnosis);
			dto.setMedicines(medicines);
			
			list.add(dto);
		}
		return list;
	}


	public VisitDataDTO getAdjustedMedicinesAndHistoryByPatient(String email) {
		VisitDataDTO dto = new VisitDataDTO();
		PatientsEntity patient = credentialsDAO.findByEmail(email).getPatient();
		dto = getHistoryByPatientId(patient.getId()).get( 0 );
		List<PatientHistoryEntity> history = patient.getHistories();
		List<Generic2> list = new ArrayList<>();
		Date now = new Date();
		Generic2 g;
		for(PatientHistoryEntity h:history) {
			for(MedicinesEntity med:h.getMedicines()) {
				String type = med.getDurationType();
				int factor = 0;
				if( type.equals("DAYS") )
					factor = 1;
				else if(type.equals("WEEKS"))
					factor = 7;
				else if(type.equals("MONTHS"))
					factor = 30;
				else if(type.equals("YEARS"))
					factor = 365;
				
				
				long diff = now.getTime() - h.getDate().getTime()  ;
			    long days =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) ;
			    int daysLeft = med.getDuration()*factor - (int)days;
			    if( daysLeft >= 0 ) {
			    	g = new Generic2();
			    	g.setId(med.getId());
			    	g.setTerm(med.getTerm().getTerm());
			    	g.setDuration( daysLeft );
			    	g.setDurationType("DAYS");
			    	list.add(g);
			    }
				
			}
		}
		dto.setMedicines(list);
		return dto;
	}


	public List<VisitDataDTO> getHistoryByPatientEmail(String email) {
		return getHistoryByPatientId( credentialsDAO.findByEmail(email).getPatient().getId() );
		
	}


	public List<DoctorDetailsDTO> getDoctorsByTerm(String term) {
		return doctorsDAO.findByFullNameOrClinic(term).stream().map(d -> {
			DoctorDetailsDTO dto = new DoctorDetailsDTO();
			dto.setFullName(d.getFullName());
			dto.setId(d.getId());
			dto.setClinic(d.getClinic());
			dto.setQualification(d.getQualification());
			return dto;
		}).collect(Collectors.toList());
	}

	@Transactional
	public boolean addHistoryApproval(ApprovalDTO dto) {
		//check if the combo exists
		if( historyApprovalDAO.findByDoctorAndPatient(doctorsDAO.findById(dto.getDoctor()),patientsDAO.findById(dto.getPatient())).size() == 1
				)
			return false;
		else {
			HistoryPermissionEntity p = new HistoryPermissionEntity();
			p.setDoctor(doctorsDAO.findById(dto.getDoctor()));
			p.setPatient(patientsDAO.findById(dto.getPatient()));
			p.setGranted(false);
			historyApprovalDAO.save(p);
			return true;
		}
	}


	public List<AccessDTO> getPermissionsByPatientEmail(String email) {
		// TODO Auto-generated method stub
		return historyApprovalDAO.findByPatient(
				credentialsDAO.findByEmail(email).getPatient()).stream().map(p -> {
					AccessDTO dto = new com.malay.emr.dto.AccessDTO();
					dto.setAllowed(p.getGranted());
					dto.setId(p.getId());
					dto.setDoctor(new DoctorDetailsDTO(p.getDoctor().getFullName(), p.getDoctor().getClinic(), p.getDoctor().getQualification()));
					return dto;
				}).collect(Collectors.toList());
	}


	public void togglePermissionsById(int id) {
	HistoryPermissionEntity	app = historyApprovalDAO.findById(id);
		if(app.getGranted()==false)
			app.setGranted(true);
		else
			app.setGranted(false);
	}
	
	
}
