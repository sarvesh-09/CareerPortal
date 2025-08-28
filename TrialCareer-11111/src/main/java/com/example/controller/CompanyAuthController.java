package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Application;
import com.example.model.Company;
import com.example.model.Job;
import com.example.service.ApplicationService;
import com.example.service.CompanyService;
import com.example.service.JobService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyAuthController {

	@Autowired
    private CompanyService companyService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ApplicationService applicationService;

	

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/register";
    }

    @PostMapping("/register")
    public String registerCompany(@ModelAttribute Company company, Model model) {
        if (companyService.getByEmail(company.getEmail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return "company/register";
        }
        companyService.save(company);
        model.addAttribute("message", "Company registered! Please login.");
        return "company/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("company", new Company());
        return "company/login";
    }

    @PostMapping("/login")
    public String loginCompany(@ModelAttribute Company company, HttpSession session, Model model) {
        Company existingCompany = companyService.getByEmail(company.getEmail());
        if (existingCompany == null || !existingCompany.getPassword().equals(company.getPassword())) {
            model.addAttribute("error", "Invalid email or password");
            return "company/login";
        }

        session.setAttribute("company", existingCompany);
        return "redirect:/company/Comhome";
    }

    @GetMapping("/Comhome")
    public String companyHome(HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        model.addAttribute("company", company);
        model.addAttribute("jobs", jobService.getJobsByCompany(company)); // ✅ add jobs

        //model.addAttribute("name", company.getName());
        return "Comhome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/company/login";
    }
    
    /////////////////////////
    
    @GetMapping("/profile")
    public String showCompanyProfile(HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        model.addAttribute("company", company);
        return "company/profile";
    }

    @PostMapping("/profile")
    public String updateCompanyProfile(@ModelAttribute Company updatedCompany,
                                       HttpSession session,
                                       Model model) {
        Company sessionCompany = (Company) session.getAttribute("company");
        if (sessionCompany == null) {
            return "redirect:/company/login";
        }

        // Keep the original ID
        updatedCompany.setId(sessionCompany.getId());

        Company saved = companyService.save(updatedCompany);
        session.setAttribute("company", saved); // Update session
        model.addAttribute("company", saved);
        model.addAttribute("success", "Profile updated successfully!");

        return "company/profile";
    }
    
    
   //////////////////
   
    @GetMapping("/post-job")
    public String showJobForm(Model model, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        model.addAttribute("job", new Job());
        return "company/post-job";
    }
    
    
    
    

    @PostMapping("/post-job")
    public String saveJob(@ModelAttribute Job job, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        job.setCompany(company);
        job.setPostedDate(LocalDate.now()); // ✅ set the date here
        jobService.save(job);
        model.addAttribute("success", "Job posted successfully!");
        return "redirect:/company/jobs";
    }

    @GetMapping("/jobs")
    public String listCompanyJobs(HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        model.addAttribute("company", company); // ✅ add this
        model.addAttribute("jobs", jobService.getJobsByCompany(company));
        return "company/jobs";
    }
    
    
    /////////////////////////
    
    
    @GetMapping("/edit-job/{id}")
    public String editJobForm(@PathVariable Long id, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        Job job = jobService.getJobById(id);
        if (job == null || !job.getCompany().getId().equals(company.getId())) {
            return "redirect:/company/jobs";
        }

        model.addAttribute("job", job);
        return "company/edit-job";
    }

    @PostMapping("/jobs/update")
    public String updateJob(@ModelAttribute Job job, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        job.setCompany(company);
        jobService.save(job);
        return "redirect:/company/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        Job job = jobService.getJobById(id);
        if (job != null && job.getCompany().getId().equals(company.getId())) {
            jobService.deleteJob(id);
        }

        return "redirect:/company/jobs";
    }

    
 // ✅ View applications for a specific job
    @GetMapping("/applications/{id}")
    public String viewJobApplications(@PathVariable Long id, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        Job job = jobService.getJobById(id);
        if (job == null || !job.getCompany().getId().equals(company.getId())) {
            return "redirect:/company/jobs";
        }

        model.addAttribute("job", job);
        model.addAttribute("applications", applicationService.getApplicationsByJob(job));
        return "company/applications";
    }
    
    
    
 // Delete application
    @GetMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id, HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        Application application = applicationService.getApplication(id);
        if (application != null && 
            application.getJob().getCompany().getId().equals(company.getId())) {
            applicationService.delete(id);
        }
        return "redirect:/company/applications/" + application.getJob().getId();
    }

    // Schedule interview (just a placeholder — you can make a page)
    @GetMapping("/applications/schedule/{id}")
    public String scheduleInterview(@PathVariable Long id, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) return "redirect:/company/login";

        Application application = applicationService.getApplication(id);
        if (application == null || !application.getJob().getCompany().getId().equals(company.getId())) {
            return "redirect:/company/jobs";
        }

        model.addAttribute("application", application);
        return "company/schedule-interview";
    }

    @PostMapping("/applications/schedule")
    public String saveInterviewSchedule(@RequestParam("id") Long id,
                                        @RequestParam("dateTime") String dateTime,
                                        HttpSession session) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }

        Application application = applicationService.getApplication(id);
        if (application != null && 
            application.getJob().getCompany().getId().equals(company.getId())) {

            application.setStatus("Interview Scheduled on " + dateTime);
            applicationService.save(application);

            return "redirect:/company/applications/" + application.getJob().getId();
        }

        // If invalid or not found, go back to jobs
        return "redirect:/company/jobs";
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 // View applications
   /* @GetMapping("/applications")
    public String viewApplications(HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        model.addAttribute("applications", applicationService.getApplicationsByCompany(company));
        return "company/applications";
    }

    // Edit application
    @GetMapping("/applications/edit/{id}")
    public String editApplication(@PathVariable Long id, HttpSession session, Model model) {
        Company company = (Company) session.getAttribute("company");
        if (company == null) {
            return "redirect:/company/login";
        }
        Application application = applicationService.getApplication(id);
        model.addAttribute("application", application);
        return "company/edit-application";
    }

    @PostMapping("/applications/update")
    public String updateApplication(@ModelAttribute Application application) {
        applicationService.save(application);
        return "redirect:/company/applications";
    }

    // Delete application
    @GetMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.delete(id);
        return "redirect:/company/applications";
    }*/

}
