package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Application;
import com.example.model.Company;
import com.example.model.Job;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobCompany(Company company); // Get apps for jobs posted by a company
    
    List<Application> findByJob(Job job);

	
}
