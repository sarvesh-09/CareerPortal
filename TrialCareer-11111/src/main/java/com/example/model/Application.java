package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	
	private LocalDateTime interviewDateTime;
	

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String applicantName;
    private String email;
    private String resumePath;
    private LocalDate appliedDate;
    private String status; // e.g. Pending, Reviewed, Accepted, Rejected

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public LocalDateTime getInterviewDateTime() {
        return interviewDateTime;
    }

    public void setInterviewDateTime(LocalDateTime interviewDateTime) {
        this.interviewDateTime = interviewDateTime;
    }

	
	
	public Application(Long id, User user, Job job, String applicantName, String email, String resumePath,
			LocalDate appliedDate, String status) {
		super();
		this.id = id;
		this.user = user;
		this.job = job;
		this.applicantName = applicantName;
		this.email = email;
		this.resumePath = resumePath;
		this.appliedDate = appliedDate;
		this.status = status;
	}
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    
    
}
