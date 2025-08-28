package com.example.model;

import java.time.LocalDate;

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
@Data
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fullName;

	    @Column(unique = true)
	    private String email;

	    private String password;

	    private long Mobile;
	    
	    private String Gender;
	    
		private LocalDate DOB;
		
		private String education;
		
		private String experience;
		
		private String skills;
		
		private String resumePath;
		  
		private String photoPath;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
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

		public long getMobile() {
			return Mobile;
		}

		public void setMobile(long mobile) {
			Mobile = mobile;
		}

		public String getGender() {
			return Gender;
		}

		public void setGender(String gender) {
			Gender = gender;
		}

		public LocalDate getDOB() {
			return DOB;
		}

		public void setDOB(LocalDate dOB) {
			DOB = dOB;
		}

		public String getEducation() {
			return education;
		}

		public void setEducation(String education) {
			this.education = education;
		}

		public String getExperience() {
			return experience;
		}

		public void setExperience(String experience) {
			this.experience = experience;
		}

		public String getSkills() {
			return skills;
		}

		public void setSkills(String skills) {
			this.skills = skills;
		}

		public String getResumePath() {
			return resumePath;
		}

		public void setResumePath(String resumePath) {
			this.resumePath = resumePath;
		}

		public String getPhotoPath() {
			return photoPath;
		}

		public void setPhotoPath(String photoPath) {
			this.photoPath = photoPath;
		}

		public User(Long id, String fullName, String email, String password, long mobile, String gender, LocalDate dOB,
				String education, String experience, String skills, String resumePath, String photoPath) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.email = email;
			this.password = password;
			Mobile = mobile;
			Gender = gender;
			DOB = dOB;
			this.education = education;
			this.experience = experience;
			this.skills = skills;
			this.resumePath = resumePath;
			this.photoPath = photoPath;
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
			
		
	    
	    
	    
			    
	    
}

