package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Company;
import com.example.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompany(Company company);

}
