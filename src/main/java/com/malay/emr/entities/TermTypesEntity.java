package com.malay.emr.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="term_types")
public class TermTypesEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@Column(name="type")
	private String type;
	
	@OneToMany(mappedBy = "type")
	private List<SavedTermsEntity> terms;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SavedTermsEntity> getTerms() {
		return terms;
	}

	public void setTerms(List<SavedTermsEntity> terms) {
		this.terms = terms;
	}
	
	
	
	
}
