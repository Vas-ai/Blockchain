package com.malay.emr.entities;

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
@Table(name="saved_terms")
public class SavedTermsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@Column(name="term")
	private String term;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public TermTypesEntity getType() {
		return type;
	}

	public void setType(TermTypesEntity type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="type")
	private TermTypesEntity type;
	
	@OneToMany(mappedBy="term")
	List<ComplaintsEntity> complaints;
	
	@OneToMany(mappedBy="term")
	List<DiagnosisEntity> diagnosis;
	
	@OneToMany(mappedBy="term")
	List<MedicinesEntity> medicines;
	
	@OneToMany(mappedBy="term")
	List<TestsEntity> tests;
	
	

	public List<TestsEntity> getTests() {
		return tests;
	}

	public void setTests(List<TestsEntity> tests) {
		this.tests = tests;
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

	public List<ComplaintsEntity> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<ComplaintsEntity> complaints) {
		this.complaints = complaints;
	}
	
	
	
}
