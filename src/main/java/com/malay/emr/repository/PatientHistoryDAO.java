package com.malay.emr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.PatientHistoryEntity;
import com.malay.emr.entities.PatientsEntity;

@Repository
public interface PatientHistoryDAO extends JpaRepository<PatientHistoryEntity, Integer>{

	List<PatientHistoryEntity> findByPatientOrderByIdDesc(PatientsEntity patient);
	
	PatientHistoryEntity findById(int id);
	
}
