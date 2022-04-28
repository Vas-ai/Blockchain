package com.malay.emr.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="patients")
public class PatientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="given_name")
    private String givenName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="sex")
    private String sex;

    @Column(name="contact_no")
    private String mobile;
    
    @Column(name="dob")
    private Date dob;

    public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@OneToOne
	@JoinColumn(name = "credentials_id")
    private CredentialsEntity credentials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CredentialsEntity getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsEntity credentials) {
        this.credentials = credentials;
    }
}
