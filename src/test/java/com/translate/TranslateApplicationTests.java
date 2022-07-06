package com.translate;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.translate.model.UserEntity;
import com.translate.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TranslateApplicationTests {

	@Autowired
	UserRepository userRepository;
	//@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@Test
	public void find()
	{
		UserEntity user = new UserEntity();
		user.setEmail("sguerfi@yahoo.com");
		user.setFirstName("Souhila");
		user.setLastName("GUERFI");
		user.setVerification(true);
		user.setPassword(passwordEncoder.encode("123"));
		user.setRole("ADMIN");
		userRepository.save(user);

		UserEntity found = userRepository.findByEmail(user.getEmail());
		assertThat(found.getEmail()).isEqualTo("sguerfi@yahoo.com");
	}
	
	@Test
	public void create()
	{
		UserEntity user = new UserEntity();
		user.setEmail("user@email.com");
		user.setFirstName("Prenom");
		user.setLastName("Nom");
		user.setPassword(passwordEncoder.encode("azertyuiop"));
		userRepository.save(user);

		UserEntity found = userRepository.findByEmail(user.getEmail());
		
		SoftAssertions assertBundle = new SoftAssertions();
		assertBundle.assertThat(found.getEmail()).isEqualTo("user@email.com");
		assertBundle.assertThat(found.getPassword()).isEqualTo(passwordEncoder.encode("azertyuiop"));
		assertBundle.assertThat(found.isVerified()).isEqualTo(false);
		assertBundle.assertThat(found.getRole()).isEqualTo("USER");
		assertBundle.assertThat(found.getFirstName()).isEqualTo("Prenom");
		assertBundle.assertThat(found.getLastName()).isEqualTo("Nom");
		assertBundle.assertAll();
	}
	
	@Test
	public void verify()
	{
		UserEntity user = userRepository.findByEmail("user@email.com");
		user.setVerification(true);
		userRepository.save(user);
		
		UserEntity found = userRepository.findByEmail(user.getEmail());
		assertThat(found.isVerified()).isEqualTo(true);
	}
	
	@Test
	public void delete()
	{
		UserEntity found = userRepository.findByEmail("user@email.com");
		userRepository.delete(found);
		
		UserEntity notfound = userRepository.findByEmail("user@email.com");
		assertThat(notfound).isEqualTo(null);
	}
}