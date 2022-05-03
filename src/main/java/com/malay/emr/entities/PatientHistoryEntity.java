package com.malay.emr.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="history")
public class PatientHistoryEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@Column(name="bp_1")
	private int bp1;
	
	@Column(name="bp_2")
	private int bp2;
	
	@Column(name="pulse")
	private int pulse;
	
	@Column(name="height")
	private int height;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="temp")
	private int temp;
	
	@Column(name="spo_2")
	private int spo2;
	
	@Column(name="advice")
	private String advice;
	
	@Column(name="date")
	private Date date = new Date();
	
	@OneToMany(mappedBy = "history")
	private List<ComplaintsEntity> complaints;
	
	@OneToMany(mappedBy = "history")
	private List<DiagnosisEntity> diagnosis;
	
	
	@OneToMany(mappedBy = "history")
	private List<MedicinesEntity> medicines;
	
	@ManyToOne
	@JoinColumn(name="patient")
	private PatientsEntity patient;
	
	@OneToMany(mappedBy = "history")
	private List<TestsEntity> tests;
	
	

	public List<TestsEntity> getTests() {
		return tests;
	}

	public void setTests(List<TestsEntity> tests) {
		this.tests = tests;
	}

	public PatientsEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientsEntity patient) {
		this.patient = patient;
	}

	public List<MedicinesEntity> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicinesEntity> medicines) {
		this.medicines = medicines;
	}

	public List<DiagnosisEntity> getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(List<DiagnosisEntity> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBp1() {
		return bp1;
	}

	public void setBp1(int bp1) {
		this.bp1 = bp1;
	}

	public int getBp2() {
		return bp2;
	}

	public void setBp2(int bp2) {
		this.bp2 = bp2;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getSpo2() {
		return spo2;
	}

	public void setSpo2(int spo2) {
		this.spo2 = spo2;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<ComplaintsEntity> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<ComplaintsEntity> complaints) {
		this.complaints = complaints;
	}
	
	
	
	
}
