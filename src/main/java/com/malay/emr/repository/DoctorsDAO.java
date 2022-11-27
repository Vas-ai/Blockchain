package com.malay.emr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malay.emr.entities.DoctorEntity;

public interface DoctorsDAO extends JpaRepository<DoctorEntity,Integer>{
	@Query("select p from DoctorEntity p where p.fullName like %?1% or p.clinic like %?1% ")
	List<DoctorEntity> findByFullNameOrClinic(String term);

	DoctorEntity findById(int id);
	
}
