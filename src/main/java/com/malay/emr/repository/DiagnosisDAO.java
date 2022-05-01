package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.DiagnosisEntity;

@Repository
public interface DiagnosisDAO extends JpaRepository<DiagnosisEntity, Integer> {

}
