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
			.antMatchers("/topic/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			.antMatchers("/calculator").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/topic/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/course/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/home").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/user/check/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/user/delete/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/user/edit/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/application/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/application/check/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/application/delete/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/application/edit/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/add/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/delete/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/download/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/category/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/category/check/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/category/delete/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/category/edit/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/url/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/url/add/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/url/delete/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/attachment/url/edit/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/interface/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/interface/sign/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/interface/sign/check/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			// .antMatchers("/interface/sign/check2/*").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
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
