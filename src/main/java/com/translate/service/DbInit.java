package com.translate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.translate.model.Role;
import com.translate.model.UserEntity;
import com.translate.repository.RoleRepository;
import com.translate.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception
	{
		userRepository.deleteAll();
		
		UserEntity user1 = new UserEntity();
		user1.setEmail("sguerfi12@yahoo.com");
		user1.setFirstName("TOTO");
		user1.setLastName("TOTO");
		user1.setAccountVerified(true);
		user1.setPassword(passwordEncoder.encode("1234"));
		user1.setRole("USER");
		
		UserEntity user2 = new UserEntity();
		user2.setEmail("sguerfi12@gmail.com");
		user2.setFirstName("TITI");
		user2.setLastName("TITI");
		user2.setAccountVerified(true);
		user2.setPassword(passwordEncoder.encode("12345"));
		user2.setRole("ADMIN");

		userRepository.saveAndFlush(user1);
		userRepository.saveAndFlush(user2);
	}
}
