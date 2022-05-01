package com.malay.emr.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malay.emr.entities.AppointmentsEntity;
import com.malay.emr.entities.DoctorEntity;

@Repository
public interface AppointmentsDAO extends JpaRepository<AppointmentsEntity,Integer>{

	AppointmentsEntity findByDoctorAndTime(DoctorEntity doctor, Date time);

}
