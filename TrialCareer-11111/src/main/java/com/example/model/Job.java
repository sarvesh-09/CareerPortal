package com.example.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String description;
	    private String location;
	    private String type; // e.g., Full-time, Part-time
	    private Double salary;
	    private LocalDate postedDate; // <-- Add this


	    
	    @ManyToOne
	    @JoinColumn(name = "company_id", nullable = false)
	    private Company company;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public LocalDate getPostedDate() {
			return postedDate;
		}

		public void setPostedDate(LocalDate postedDate) {
			this.postedDate = postedDate;
		}
		
		

		public Job(Long id, String title, String description, String location, String type, Double salary,
				LocalDate postedDate, Company company) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.location = location;
			this.type = type;
			this.salary = salary;
			this.postedDate = postedDate;
			this.company = company;
		}

		
		public Job() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
