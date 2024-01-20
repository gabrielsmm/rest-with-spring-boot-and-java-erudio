package br.com.erudio.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.erudio.security.jwt.JwtConfigurer;
import br.com.erudio.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private JwtTokenProvider tokenProvider;
	
	public SecurityConfig(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
		return passwordEncoder;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
	         throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				   .cors().and().httpBasic().disable()
				   .csrf().disable()
				   .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .authorizeHttpRequests(req -> req
						.requestMatchers("/auth/signin", "/auth/refresh/**", "/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
						.requestMatchers("/api/**").authenticated()
						.requestMatchers("/users").denyAll())
				   .apply(new JwtConfigurer(tokenProvider))
				   .and().build();
	}
	
}
