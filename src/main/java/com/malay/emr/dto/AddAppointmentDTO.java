package com.malay.emr.dto;

import java.util.Date;

public class AddAppointmentDTO {
	private int patientId;
	private Date time;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public AddAppointmentDTO() {}
	
	public AddAppointmentDTO(int patientId, Date time) {
		super();
		this.patientId = patientId;
		this.time = time;
	}
	
	
}
