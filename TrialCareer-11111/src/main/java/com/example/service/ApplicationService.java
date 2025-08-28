package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Application;
import com.example.model.Company;
import com.example.model.Job;
import com.example.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
    private ApplicationRepository applicationRepository;
	
	public Application apply(Application application) {
        application.setAppliedDate(LocalDate.now());
        application.setStatus("Pending");
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }
	
	
              ////////////////////

    public List<Application> getApplicationsByCompany(Company company) {
        return applicationRepository.findByJobCompany(company);
    }

    public Application getApplication(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}
