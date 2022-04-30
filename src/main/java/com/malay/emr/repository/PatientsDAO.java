package com.malay.emr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.PatientsEntity;

@Repository
public interface PatientsDAO extends JpaRepository<PatientsEntity,Integer> {
	@Query("select p from PatientsEntity p where p.givenName like %?1% or p.lastName like %?1% or p.mobile like %?1% ")
	List<PatientsEntity> findByGivenNameOrLastNameOrMobile(String term);
}
