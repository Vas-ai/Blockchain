package com.malay.emr.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicines")
public class MedicinesEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name="medicine")
	private SavedTermsEntity term;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="duration_type")
	private String durationType;
	
	@ManyToOne
	@JoinColumn(name="prescription")
	private PatientHistoryEntity history;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SavedTermsEntity getTerm() {
		return term;
	}

	public void setTerm(SavedTermsEntity term) {
		this.term = term;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public PatientHistoryEntity getHistory() {
		return history;
	}

	public void setHistory(PatientHistoryEntity history) {
		this.history = history;
	}
	
	
	
}
