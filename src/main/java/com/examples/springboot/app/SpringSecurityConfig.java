package com.examples.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

//import com.examples.springboot.app.auth.handler.LoginSuccesHandler;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig {

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	@Bean
	public UserDetailsService userDetailsService() throws Exception {

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("oscar").password(passwordEncoder().encode("12345")).roles("USER").build());

		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER").build());

		return manager;
	}
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("\"/\", \"/css/**\", \"/js/**\", \"/images/**\", \"/listar\"").permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(formLogin -> formLogin
	                .loginPage("/login")
	                .permitAll()
	            )
	            .rememberMe(Customizer.withDefaults());

	        return http.build();
	    }
	}

	//@Autowired
	// private LoginSuccesHandler successHandler;
/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) ->

		authorize.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
				/*
				 * .requestMatchers("/uploads/**").hasRole("USER").requestMatchers("/ver/**").
				 * hasRole("USER")
				 * .requestMatchers("/factura/**").hasRole("ADMIN").requestMatchers("/form/**").
				 * hasRole("ADMIN") .requestMatchers("/eliminar/**").hasRole("ADMIN")
				 */
				//.requestMatchers("/").authenticated().anyRequest().denyAll()
				//.successHandler(successHandler)
				//.loginPage("/login")
				//.permitAll()
	//	);

		//return http.build();

	//} */

}
