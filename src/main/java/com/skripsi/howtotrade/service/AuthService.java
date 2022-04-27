package com.skripsi.howtotrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skripsi.howtotrade.mapper.UserMapper;

@Service
public class AuthService {
	@Autowired
	private UserMapper userMapper;
	
	public String isUsernameExist(String username) {
		System.out.println("Cheking username...");
		String status = userMapper.getUser(username);
		if (status.equals("0")) return "Blokir";
		else if (status.equals("1")) return "Aktif";
		else return "TidakTerdaftar";
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
		if(!isUsernameExist(username).equals("TidakTerdaftar")){
			System.out.println("Username exist!...");
			return false;
		}
		userMapper.insertUser(username, "test@email.com", password, userRole, "1");
		return true;
	}
}
