package com.malay.emr.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentDTo {
	private int patientId;
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	private int id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date time;
	private String fullName;
	private String sex;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public AppointmentDTo(int id, Date time, String fullName, String sex, Date dob, int patientId) {
		super();
		this.id = id;
		this.time = time;
		this.fullName = fullName;
		this.sex = sex;
		this.dob = dob;
		this.patientId = patientId;
	}
	
	
	
}
