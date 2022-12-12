package com.malay.emr.dto;

import java.io.Serializable;

public class Generic1 implements Serializable {
	@Override
	public String toString() {
		return "Generic1 [term=" + term + ", id=" + id + "]";
	}


	public void setTerm(String term) {
		this.term = term;
	}

	private String term;
	private int id;

	public String getTerm() {
		return term;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Generic1() {}
}
