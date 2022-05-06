package com.skripsi.howtotrade.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	// @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.httpBasic().
		and().
		authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/*").permitAll()
			.antMatchers("/topic/all").permitAll()
			.antMatchers("/course/all").permitAll()
			.antMatchers("/course/certificate.html").permitAll()
			.antMatchers("/topic/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			.antMatchers("/calculator").access("hasAnyRole('ROLE_USER')")
			.antMatchers("/course/quiz/*").access("hasAnyRole('ROLE_USER')")
			.antMatchers("/course/*").access("hasAnyRole('ROLE_USER')")
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
		
		http.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.loginProcessingUrl("/doLogin")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true");
		
		http.logout()
			.logoutUrl("/doLogout")
			.logoutSuccessUrl("/");
	}
}
