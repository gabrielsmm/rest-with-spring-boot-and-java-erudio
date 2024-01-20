package br.com.erudio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		encoders.put("bcrypt", new BCryptPasswordEncoder());
//		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
//		passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
//		
//		String result = passwordEncoder.encode("admin123");
//		System.out.println("My hash: " + result);
	}

}
