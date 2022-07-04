package com.translate;

import static org.assertj.core.api.Assertions.assertThat;

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
	public void whenFindByEmail_thenReturnUser()
	{
		UserEntity user = new UserEntity();
		user.setEmail("sguerfi@yahoo.com");
		user.setFirstName("Souhila");
		user.setLastName("GUERFI");
		user.setAccountVerified(true);
		user.setPassword(passwordEncoder.encode("123"));
		user.setRole("ADMIN");
		
		userRepository.save(user);

		UserEntity found = userRepository.findByEmail(user.getEmail());
		assertThat(found.getEmail()).isEqualTo("sguerfi@yahoo.com");
	}
}