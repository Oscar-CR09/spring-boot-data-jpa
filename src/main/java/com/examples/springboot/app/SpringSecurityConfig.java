package com.examples.springboot.app;

//import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.examples.springboot.app.auth.handler.LoginSuccesHandler;
import com.examples.springboot.app.models.service.JpaUserDetailsService;


@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private LoginSuccesHandler successHandler;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    //@Autowired
   // private DataSource dataSource;

    @Autowired
    private JpaUserDetailsService userDetailsService;
    
	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/", "/css/**","/js/**","/images/**","/listar**","/locale","/api/clientes/**").permitAll()
					.anyRequest().authenticated())
				
					.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/error_403"))
					.formLogin(formLogin -> formLogin.successHandler(successHandler).loginPage("/login")
					//.logout((logout) -> logout.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
					//.logout((logout) -> logout.logoutUrl("/","/login"))
					.permitAll())
					.rememberMe(Customizer.withDefaults());
			;

			return http.build();
		}
	}
	//public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
    @Autowired
    public void userDetailsService(AuthenticationManagerBuilder build) throws Exception
	{
		
    	build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
    	
    	//build.jdbcAuthentication()
    	//.dataSource(dataSource)
		//.usersByUsernameQuery("select usuario, passoword, enabled from usuarios where usuario=?")
		//.authoritiesByUsernameQuery("select u.usuario, a.authority from authorities a inner join usuarios u on (a.user_id=u.id) where u.usuario=?");
	}

}
