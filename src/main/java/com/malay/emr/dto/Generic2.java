package com.malay.emr.dto;

import java.io.Serializable;

public class Generic2 implements Serializable{
	@Override
	public String toString() {
		return "Generic2 [term=" + term + ", duration=" + duration + ", id=" + id + ", durationType=" + durationType
				+ "]";
	}
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