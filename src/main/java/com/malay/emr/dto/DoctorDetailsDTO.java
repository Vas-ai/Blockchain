package com.malay.emr.dto;

import java.io.Serializable;

public class DoctorDetailsDTO implements Serializable {
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String fullName;
	private String clinic;
	private String qualification;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public DoctorDetailsDTO(String fullName, String clinic, String qualification) {
		super();
		this.fullName = fullName;
		this.clinic = clinic;
		this.qualification = qualification;
	}
	public DoctorDetailsDTO() {}
	
}
