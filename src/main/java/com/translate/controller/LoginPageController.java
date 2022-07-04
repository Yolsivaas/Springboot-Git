package com.translate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.translate.model.UserEntity;
import com.translate.repository.UserRepository;

@Controller
public class LoginPageController
{
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/home")
	public String home(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", userRepository.findByEmail(auth.getName()));
		return "home";
	}
	
	@GetMapping("/_menu")
	public String _menu()
	{
		return "_menu";
	}
}