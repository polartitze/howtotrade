package com.skripsi.howtotrade.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

@Service
public class AuthService {
	@Autowired
	private UserMapper userMapper;
	
	public boolean isUsernameExist(String username) {
		System.out.println("Cheking username...");
		String exist = userMapper.getUser(username);
		if (exist != null) return true;
		return false;
	}

	public boolean checkPassword(String username, String password){
		String exist = userMapper.getPassword(username, password);
		if (exist != null) {
			throw new UsernameNotFoundException(username);
			// return true;
		}
		return false;
	}

	
	public boolean insertUser(String username, String password, int userRole) {
		if(isUsernameExist(username)){
			System.out.println("Username exist!...");
			return false;
		}
		userMapper.insertUser(username, "test@email.com", password, userRole, "1");
		return true;
	}
}
