package com.examples.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.examples.springboot.app.auth.handler.LoginSuccesHandler;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

	
    @Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select usuario, passoword, enabled from usuarios where usuario=?")
		.authoritiesByUsernameQuery("select u.usuario, a.authority from authorities a inner join usuarios u on (a.user_id=u.id) where u.usuario=?");
		
	}
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("\"/\", \"/css/**\", \"/js/**\", \"/images/**\", \"/listar\"").permitAll()
					.anyRequest().authenticated())
					.formLogin(formLogin -> formLogin.successHandler(successHandler).loginPage("/login")

							.permitAll())

					.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/error_403"))

					.logout((logout) -> logout.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
					// .logout((logout) -> logout.logoutUrl("/my/success/endpoint"))

					.rememberMe(Customizer.withDefaults());
			;

			return http.build();
		}
	}

	@Autowired
	private LoginSuccesHandler successHandler;

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http.authorizeHttpRequests((authorize) ->
	 * 
	 * authorize.requestMatchers("/", "/css/**", "/js/**", "/images/**",
	 * "/listar").permitAll() /*
	 * .requestMatchers("/uploads/**").hasRole("USER").requestMatchers("/ver/**").
	 * hasRole("USER")
	 * .requestMatchers("/factura/**").hasRole("ADMIN").requestMatchers("/form/**").
	 * hasRole("ADMIN") .requestMatchers("/eliminar/**").hasRole("ADMIN")
	 */
	// .requestMatchers("/").authenticated().anyRequest().denyAll()
	// .successHandler(successHandler)
	// .loginPage("/login")
	// .permitAll()
	// );

	// return http.build();

	// } */

}
