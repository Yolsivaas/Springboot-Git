package com.translate.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import com.translate.model.UserEntity;
import com.translate.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService
{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		final UserEntity customer = userRepository.findByEmail(email);
		if (customer == null) {
			throw new UsernameNotFoundException(email);
		}
		
		email = customer.getEmail();
		UserDetails user = User.withUsername(email)
			.password(customer.getPassword())
			.roles(customer.getRole() == null ? "USER" : customer.getRole())
			.build();
		return user;
	}
}