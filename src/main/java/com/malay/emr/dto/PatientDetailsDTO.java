package com.malay.emr.dto;

import java.io.Serializable;
import java.util.Date;

public class PatientDetailsDTO implements Serializable {
	private String givenName;
	private String lastName;
	private Date dob;
	private String sex;
	private String mobile;
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public PatientDetailsDTO() {}
	
	public PatientDetailsDTO(String givenName, String lastName, Date dob, String sex, String mobile) {
		super();
		this.givenName = givenName;
		this.lastName = lastName;
		this.dob = dob;
		this.sex = sex;
		this.mobile = mobile;
	}
	
	
	
}
