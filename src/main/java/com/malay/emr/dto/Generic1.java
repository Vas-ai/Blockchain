package com.malay.emr.dto;

public class Generic1 {
	public void setTerm(String term) {
		this.term = term;
	}

	private String term;

	public String getTerm() {
		return term;
	}

	public Generic1(String term) {
		super();
		this.term = term;
	}
	public Generic1() {}
}
