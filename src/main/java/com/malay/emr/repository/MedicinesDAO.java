package com.malay.emr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.MedicinesEntity;

@Repository
public interface MedicinesDAO extends JpaRepository<MedicinesEntity, Integer> {

}
