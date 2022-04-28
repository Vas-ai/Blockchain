package com.malay.emr.repository;

import com.malay.emr.entities.PatientsEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsDAO extends JpaRepository<PatientsEntity,Integer> {
	List<PatientsEntity> findByGivenNameContainsOrLastNameContainsOrMobileContains(String term1,String term2,String term3);
}
