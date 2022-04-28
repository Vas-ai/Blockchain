package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malay.emr.entities.DoctorEntity;

public interface DoctorsDAO extends JpaRepository<DoctorEntity,Integer>{

	
	
}
