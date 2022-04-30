package com.malay.emr.entities;

import javax.persistence.*;

@Entity
@Table(name="credentials")
public class CredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private  String password;

    
   


    @OneToOne(mappedBy = "credentials")
    private PatientsEntity patient;
    
    @OneToOne(mappedBy = "credentials")
    private DoctorEntity doctor;
    
    

    public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity doctor) {
		this.doctor = doctor;
	}

	public PatientsEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientsEntity patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
