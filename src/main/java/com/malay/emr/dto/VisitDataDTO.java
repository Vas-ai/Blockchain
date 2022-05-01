package com.malay.emr.dto;

import java.util.List;

public class VisitDataDTO {
	
	private int patientId;
	
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
	public float getTemp() {
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
	public void setTemp(float temp) {
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
	private float temp;
	private int spo2;
	private String advice;
	private List<Generic1> complaints;
	private List<Generic1> tests;
	private List<Generic2> medicines;
	private List<Generic2> diagnosis;
	public VisitDataDTO(int patientId, int bp1, int bp2, int pulse, int height, int weight, float temp, int spo2,
			String advice, List<Generic1> complaints, List<Generic1> tests, List<Generic2> medicines,
			List<Generic2> diagnosis) {
		super();
		this.patientId = patientId;
		this.bp1 = bp1;
		this.bp2 = bp2;
		this.pulse = pulse;
		this.height = height;
		this.weight = weight;
		this.temp = temp;
		this.spo2 = spo2;
		this.advice = advice;
		this.complaints = complaints;
		this.tests = tests;
		this.medicines = medicines;
		this.diagnosis = diagnosis;
	}
	public VisitDataDTO() {}
	

}



