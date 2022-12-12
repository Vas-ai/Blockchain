package com.malay.emr.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VisitDataDTO implements Serializable{
	
	@Override
	public String toString() {
		return "VisitDataDTO [patientId=" + patientId + ", givenName=" + givenName + ", lastName=" + lastName
				+ ", date=" + date + ", bp1=" + bp1 + ", bp2=" + bp2 + ", pulse=" + pulse + ", height=" + height
				+ ", weight=" + weight + ", temp=" + temp + ", spo2=" + spo2 + ", advice=" + advice + ", complaints="
				+ complaints + ", tests=" + tests + ", medicines=" + medicines + ", diagnosis=" + diagnosis + ", id="
				+ id + "]";
	}
	private int patientId;
	private String givenName;
	private String lastName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	private int bp1;
	private int bp2;
	private int pulse;
	private int height;
	public int getPatientId() {
		return patientId;
	}
	public int getBp1() {
		return bp1;
	}
	public int getBp2() {
		return bp2;
	}
	public int getPulse() {
		return pulse;
	}
	public int getHeight() {
		return height;
	}
	public int getWeight() {
		return weight;
	}
	public int getTemp() {
		return temp;
	}
	public int getSpo2() {
		return spo2;
	}
	public String getAdvice() {
		return advice;
	}
	public List<Generic1> getComplaints() {
		return complaints;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setBp1(int bp1) {
		this.bp1 = bp1;
	}
	public void setBp2(int bp2) {
		this.bp2 = bp2;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public void setSpo2(int spo2) {
		this.spo2 = spo2;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public void setComplaints(List<Generic1> complaints) {
		this.complaints = complaints;
	}
	public void setTests(List<Generic1> tests) {
		this.tests = tests;
	}
	public void setMedicines(List<Generic2> medicines) {
		this.medicines = medicines;
	}
	public void setDiagnosis(List<Generic2> diagnosis) {
		this.diagnosis = diagnosis;
	}
	public List<Generic1> getTests() {
		return tests;
	}
	public List<Generic2> getMedicines() {
		return medicines;
	}
	public List<Generic2> getDiagnosis() {
		return diagnosis;
	}
	private int weight;
	private int temp;
	private int spo2;
	private String advice;
	private List<Generic1> complaints;
	private List<Generic1> tests;
	private List<Generic2> medicines;
	private List<Generic2> diagnosis;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public VisitDataDTO() {}
	

}



