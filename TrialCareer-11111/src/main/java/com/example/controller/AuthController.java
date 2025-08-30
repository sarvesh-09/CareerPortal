package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.model.Application;
import com.example.model.Job;
import com.example.model.User;
import com.example.service.ApplicationService;
import com.example.service.JobService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/user")
public class AuthController {

    private final ApplicationService applicationService;

    @Autowired
    private UserService userService;
    
    
    @Autowired
    private JobService jobService;


    AuthController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    
    
    
    // Show registration form
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle registration submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        userService.register(user);
        model.addAttribute("message", "Registration successful. Please login.");
        return "redirect:/login";
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle login submission
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    // Show user home page
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("jobs", jobService.getAllJobs()); 
        
        return "home";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    // Show profile form
    @GetMapping("/profile")
    public String showProfileForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    // Handle profile update
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") User updatedUser,
                                HttpSession session,
                                Model model) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }

        updatedUser.setId(sessionUser.getId());
        User savedUser = userService.updateUserProfile(updatedUser);
        session.setAttribute("user", savedUser);
        model.addAttribute("user", savedUser);
        model.addAttribute("success", "Profile updated successfully!");
        return "profile";
    }
        ///////////////////////////////////////
       
        

@PostMapping("/apply/{jobId}")
public String applyForJob(@PathVariable Long jobId, HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
        return "redirect:/login";
    }

    // ✅ Get job from DB
    Job job = jobService.getJobById(jobId);
    if (job == null) {
      //  model.addAttribute("error", "Job not found!");
        return "redirect:/home?error=Job%20not%20found!";
    }

    // ✅ Prevent duplicate applications
    List<Application> existingApplications = applicationService.getApplicationsByJob(job);
    boolean alreadyApplied = existingApplications.stream()
            .anyMatch(a -> a.getUser().getId().equals(user.getId()));
    if (alreadyApplied) {
       // model.addAttribute("error", "You have already applied for this job!");
    	 return "redirect:/home?error=You%20have%20already%20applied%20for%20this%20job!";

    }

    // ✅ Create application from user profile
    Application application = new Application();
    application.setUser(user);
    application.setJob(job);
    application.setApplicantName(user.getFullName());
    application.setEmail(user.getEmail());
    application.setResumePath(user.getResumePath()); // You already store resume path in User
    application.setAppliedDate(LocalDate.now());
    application.setStatus("Pending");

    applicationService.apply(application);

    model.addAttribute("success", "Application submitted successfully!");
    return "redirect:/home?success=Application%20submitted%20successfully!";
}



@GetMapping("/applications")
public String viewMyApplications(HttpSession session, Model model) {
    User user = (User) session.getAttribute("user");
    if (user == null) return "redirect:/login";

    List<Application> myApplications = applicationService.getApplicationsByUser(user);
    model.addAttribute("applications", myApplications);

    return "my-applications";
}





}

