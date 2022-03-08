package com.skripsi.howtotrade.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
                                    .and()
                                    .withUser("admin").password("admin").roles("ADMIN");
    }


	// @Bean
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	//   // ALTHOUGH THIS SEEMS LIKE USELESS CODE,
	//   // IT'S REQUIRED TO PREVENT SPRING BOOT AUTO-CONFIGURATION
	//   return super.authenticationManagerBean();
	// }

    @Bean 
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();	//artinya password gak akan di encrypt, bisa clear text doang
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin", "/admin/*").hasAnyRole("USER", "ADMIN")
            .antMatchers("/user","/user/*").hasRole("USER")
            .antMatchers("/").permitAll()
            .antMatchers("/resource/**").permitAll()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
        .logout()
            .permitAll();
    }

}

