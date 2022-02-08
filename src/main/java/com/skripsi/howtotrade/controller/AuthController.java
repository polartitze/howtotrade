package com.skripsi.howtotrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.AuthService;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	@Autowired
	AuthService authService;
	
	@RequestMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("users", new Users());
		return "register/register";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Users users) {
		System.out.println(users.getUserName());
		String str;
		// validasi user sudah terdaftar atau belum
		if(authService.isUsernameExist(users.getUserName())) {
			System.out.println("Username sudah terdaftar");
			return "home/homepage";
		}
		
		
		// insert to db
		authService.insertUser(users);
		return "home/homepage";
	}
	
	
	
	
}
