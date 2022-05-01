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
	public Generic2(String term, int duration, String durationType) {
		super();
		this.term = term;
		this.duration = duration;
		this.durationType = durationType;
	}
	
	public Generic2() {}
	
	
}