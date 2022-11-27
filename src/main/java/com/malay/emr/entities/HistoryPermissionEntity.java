package com.malay.emr.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="history_permissions")
public class  HistoryPermissionEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@OneToOne
	@JoinColumn(name = "doctor")
	private DoctorEntity doctor;
	
	@OneToOne
	@JoinColumn(name = "patient")
	private PatientsEntity patient;
	
	@Column(name="granted")
	private Boolean granted=false;

	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientsEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientsEntity patient) {
		this.patient = patient;
	}

	public Boolean getGranted() {
		return granted;
	}

	public void setGranted(Boolean granted) {
		this.granted = granted;
	}

	public int getId() {
		return id;
	}
	
	
	
}