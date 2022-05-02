package com.skripsi.howtotrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Users;

@Service
public class AuthService {
	@Autowired
	private UserMapper userMapper;
	
	public String isUsernameExist(String username) {
		System.out.println("Cheking username...");
		String status = userMapper.getUser(username);
		String isVerified = userMapper.checkVerified(username);
		if("0".equals(isVerified)) return "Belum Verified";
	
		if ("0".equals(status)) return "Blokir";
		else if ("1".equals(status)) return "Aktif";
		else return "TidakTerdaftar";
	}

	public String checkMail(String email) {
		System.out.println("Cheking user email...");
		String check = userMapper.checkMail(email);
		if(check != null){
			return "Terdaftar";
		}
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

	
	public boolean insertUser(String username, String userEmail, String password, int userRole) {
		if(!isUsernameExist(username).equals("TidakTerdaftar")){
			System.out.println("Username exist!...");
			return false;
		}
		userMapper.insertUser(username, userEmail, password, userRole, "1");
		return true;
	}

	public void validateAccount(String username){
        userMapper.validateAccount(username);
    }

	public Users userDetaiil(String username){
		return userMapper.findUserAccount(username);
    }
}
