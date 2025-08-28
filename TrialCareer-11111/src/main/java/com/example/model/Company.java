package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String website;
    
    /////
    private String industry;
    
    private String companySize;
    
    private String headquarter;
    
    private String founded;
    
    private String specialities;

    private String address;
    
    private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getHeadquarter() {
		return headquarter;
	}

	public void setHeadquarter(String headquarter) {
		this.headquarter = headquarter;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public String getSpecialities() {
		return specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Company(Long id, String name, String email, String password, String website, String industry,
			String companySize, String headquarter, String founded, String specialities, String address,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.website = website;
		this.industry = industry;
		this.companySize = companySize;
		this.headquarter = headquarter;
		this.founded = founded;
		this.specialities = specialities;
		this.address = address;
		this.phone = phone;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
}
