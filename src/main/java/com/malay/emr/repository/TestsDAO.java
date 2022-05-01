package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.TestsEntity;

@Repository
public interface TestsDAO extends JpaRepository<TestsEntity, Integer>{

}
