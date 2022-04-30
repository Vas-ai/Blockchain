package com.malay.emr.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientSearch {
	private String givenName;
	private String lastName;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	
	private String sex;
	private String mobile;
	
	public PatientSearch(String givenName, String lastName, Date dob, String sex, String mobile,  int id) {
		super();
		this.givenName = givenName;
		this.lastName = lastName;
		this.dob = dob;
		this.sex = sex;
		this.mobile = mobile;
		
		this.id = id;
	}
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	
	public PatientSearch() {}

}
