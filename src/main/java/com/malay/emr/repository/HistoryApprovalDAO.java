package com.malay.emr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.DoctorEntity;
import com.malay.emr.entities.HistoryPermissionEntity;
import com.malay.emr.entities.PatientsEntity;

@Repository
public interface HistoryApprovalDAO extends JpaRepository<HistoryPermissionEntity,Integer>{

	List<HistoryPermissionEntity> findByDoctorAndPatient(DoctorEntity findById, PatientsEntity findById2);

	List<HistoryPermissionEntity> findByPatient(PatientsEntity patient);
	
	HistoryPermissionEntity findById(int id);

}
