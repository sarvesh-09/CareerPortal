package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Company;
import com.example.model.Job;
import com.example.repository.JobRepository;

@Service
public class JobService {

	 @Autowired
	    private JobRepository jobRepository;

	    public Job save(Job job) {
	        return jobRepository.save(job);
	    }

	    public List<Job> getJobsByCompany(Company company) {
	        return jobRepository.findByCompany(company);
	    }
	    
	    
	    
	    public Job getJobById(Long id) {
	        return jobRepository.findById(id)
	                .orElse(null);
	    }
	    
	    public List<Job> getAllJobs() {
	        return jobRepository.findAll();
	    }
	    
	    public void deleteJob(Long id) {
	        jobRepository.deleteById(id);
	    }
}
