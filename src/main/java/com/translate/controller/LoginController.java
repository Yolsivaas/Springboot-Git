package com.translate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.translate.model.UserEntity;
import com.translate.repository.UserRepository;

@Controller
public class LoginController
{
	@Autowired
	UserRepository userRepository;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model)
	{
		model.addAttribute("user", new UserEntity());
		return "register";
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute UserEntity user)
	{
		UserEntity found = userRepository.findByEmail(user.getEmail());
		if (found != null)
		{
			return "redirect:/register?error=true";
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/home")
	public String home(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserEntity current = userRepository.findByEmail(auth.getName());
		model.addAttribute("current", current );
		
		if (current.getRole().equals("ADMIN"))
		{
			model.addAttribute("list", userRepository.findAll());
			model.addAttribute("user", new UserEntity());
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/verify",method = RequestMethod.POST)
	public String verify(Model model, @ModelAttribute UserEntity user)
	{
		UserEntity found = userRepository.findByEmail(user.getEmail());
		found.setVerification(true);
		userRepository.save(found);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String remove(Model model, @ModelAttribute UserEntity user)
	{
		UserEntity found = userRepository.findByEmail(user.getEmail());
		userRepository.delete(found);
		return "redirect:/home";
	}
}