package com.malay.emr.dto;

import java.io.Serializable;

public class AccessDTO implements Serializable {
	private int id;
	private DoctorDetailsDTO doctor;
	private boolean allowed;

	private PatientDetailsDTO patientDetailsDTO;

	public PatientDetailsDTO getPatientDetailsDTO() {
		return patientDetailsDTO;
	}

	public void setPatientDetailsDTO(PatientDetailsDTO patientDetailsDTO) {
		this.patientDetailsDTO = patientDetailsDTO;
	}

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

	@Override
	public String toString() {
		return "AccessDTO{" +
				"id=" + id +
				", doctor=" + doctor +
				", allowed=" + allowed +
				", patientDetailsDTO=" + patientDetailsDTO +
				'}';
	}
}
