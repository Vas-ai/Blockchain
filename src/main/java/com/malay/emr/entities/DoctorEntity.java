package com.malay.emr.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="doctors")
public class DoctorEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	@Column(name="full_name")
    private String fullName;
	
	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	@Column(name="clinic_name")
    private String clinic;
	
	@Column(name="qualification")
    private String qualification;
	
	@OneToMany(mappedBy = "doctor")
	private List<HistoryPermissionEntity> historyPermission;
	
	public List<HistoryPermissionEntity> getHistoryPermission() {
		return historyPermission;
	}


	public void setHistoryPermission(List<HistoryPermissionEntity> historyPermission) {
		this.historyPermission = historyPermission;
	}


	@OneToOne
	@JoinColumn(name = "credentials_id")
    private CredentialsEntity credentials;

	 @OneToMany(mappedBy = "doctor")
	 private List<AppointmentsEntity> appointments;
	 
	 

	public List<AppointmentsEntity> getAppointments() {
		return appointments;
	}


	public void setAppointments(List<AppointmentsEntity> appointments) {
		this.appointments = appointments;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getClinic() {
		return clinic;
	}


	public void setClinic(String clinic) {
		this.clinic = clinic;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	

	public CredentialsEntity getCredentials() {
		return credentials;
	}


	public void setCredentials(CredentialsEntity credentials) {
		this.credentials = credentials;
	}
	
	
	
	
	
}
