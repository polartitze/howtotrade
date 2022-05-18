package com.skripsi.howtotrade.security;

import java.util.ArrayList;
import java.util.List;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users appUser = this.userMapper.findUserAccount(username);
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // String password = request.getParameter("password"); // get from request parameter
		if (appUser == null) {
			System.out.println("User not found!");
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		String role = this.userMapper.getRole(username);
		System.out.println("============ROLE: "+role);
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (role != null) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role);
			grantList.add(authority);
		}

		UserDetails userDetails = (UserDetails) new User(username, appUser.getUserPassword(), grantList);
		return userDetails;
	}

}