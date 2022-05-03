package com.malay.emr.dto;

public class Generic2{
	public void setTerm(String term) {
		this.term = term;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}
	private String term;
	private int duration;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTerm() {
		return term;
	}
	public int getDuration() {
		return duration;
	}
	public String getDurationType() {
		return durationType;
	}
	private String durationType;
	
	
	public Generic2() {}
	
	
}