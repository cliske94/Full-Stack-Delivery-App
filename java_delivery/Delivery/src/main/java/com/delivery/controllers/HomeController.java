package com.delivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.delivery.auth.MySQLUserDetailService;
import com.delivery.models.User;
import com.delivery.repositories.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MySQLUserDetailService userService;
	
	@PostMapping("/register")
	public String createuser(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
	{
		User foundUser = userRepository.findByUsername(username);
		if (foundUser == null)
		{
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			userService.Save(newUser);
			return "login";
		} else
		{
			model.addAttribute("exists", true);
			return "register";
		}
	}
	
//	@PostMapping("login")
//	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model)
//	{
//		User attemptedLoginCredentials = userRepository.findByUsername(username);
//		if(attemptedLoginCredentials == null)
//			return "register";
//		else 
//			return "index";
//	}
//	
	
    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/secure")
    public String getSecurePage() {
        return "secure";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public String getRegisterPage() {
    	return "register";
    }
}