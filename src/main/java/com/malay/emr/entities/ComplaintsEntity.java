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
@Table(name="complaints")
public class ComplaintsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name="prescription")
	private PatientHistoryEntity history;
	
	@ManyToOne
	@JoinColumn(name="complaint")
	private SavedTermsEntity term;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PatientHistoryEntity getHistory() {
		return history;
	}

	public void setHistory(PatientHistoryEntity history) {
		this.history = history;
	}

	public SavedTermsEntity getTerm() {
		return term;
	}

	public void setTerm(SavedTermsEntity term) {
		this.term = term;
	}
	
	
	
	
}
