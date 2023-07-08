package com.examples.springboot.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("oscar").password(passwordEncoder().encode("12345")).roles("USER").build());

		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER").build());

		return manager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> 
			
				authorize
				.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
						.requestMatchers("/uploads/**").hasRole("USER").requestMatchers("/ver/**").hasRole("USER")
						.requestMatchers("/factura/**").hasRole("ADMIN").requestMatchers("/form/**").hasRole("ADMIN")
						.requestMatchers("/eliminar/**").hasRole("ADMIN").requestMatchers("/").authenticated()
						.anyRequest().denyAll()
					
						        
		
					);

		return http.build();

	}
}
