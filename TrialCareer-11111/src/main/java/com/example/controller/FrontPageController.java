package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontPageController {

	@RequestMapping("/")
	public String plash() {
		
		return "frontpage/plash";
	}
	
	@RequestMapping("/user_plash")
	public String user_plash() {
		
		return "user/user_plash";
	}
	
	@RequestMapping("/company_plash")
	public String company_plash() {
		
		return "company/company_plash";
	}
}
