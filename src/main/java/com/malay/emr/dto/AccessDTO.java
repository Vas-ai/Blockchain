package com.malay.emr.dto;

public class AccessDTO {
	private int id;
	private DoctorDetailsDTO doctor;
	private boolean allowed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DoctorDetailsDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDetailsDTO doctor) {
		this.doctor = doctor;
	}
	public boolean isAllowed() {
		return allowed;
	}
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
	
}
