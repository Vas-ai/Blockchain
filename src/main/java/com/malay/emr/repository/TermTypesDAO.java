package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.TermTypesEntity;

@Repository
public interface TermTypesDAO extends JpaRepository<TermTypesEntity, Integer> {

	TermTypesEntity findFirstByType(String type);

	
}
