package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.ComplaintsEntity;

@Repository
public interface ComplaintsDAO extends JpaRepository<ComplaintsEntity, Integer>{

}
