package com.translate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;
	private String password;
	
	@Column(columnDefinition="boolean default false")
    private boolean isVerified;
	@Column(columnDefinition="varchar(255) default 'USER'")
    private String role;
    
	private String firstName;
	private String lastName;
    
	public Long getId() { return id; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public boolean isVerified() { return isVerified; }
	public String getRole() { return role; }
	public String getFullName() { return firstName + ' ' + lastName; }
	
	public void setId(Long id) { this.id = id; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setVerification(boolean isVerified) { this.isVerified = isVerified; }
	public void setRole(String role) { this.role = role; }
}


