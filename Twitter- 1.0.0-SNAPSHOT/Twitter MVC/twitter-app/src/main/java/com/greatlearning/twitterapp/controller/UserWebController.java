package com.greatlearning.twitterapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserWebController {

	@GetMapping("/")
	public String displayHomePage() {
		return "home";
	}
}
