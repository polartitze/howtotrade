package com.skripsi.howtotrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.AuthService;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;
	
	String userLogged;

	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	String username;
	@ModelAttribute("module")
    public String module() {
        return "home";
    }
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
		return "index/homepage";
    }

	@ResponseBody
	@RequestMapping(value = "/get-username", method = RequestMethod.GET)
    public String currentUsername(Principal principal){
		return principal.getName();
    }

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
		return "index/loginpage";
    }
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		System.out.println("Redirecting /user/add...");
		// System.out.println("==========username: "+principal.getName());
		Users appUser = new Users();
		model.addAttribute("appUser", appUser);
		// return "add-user";
		return "index/register";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("appUser") Users appUser, Model model) {
		System.out.println("Add new user...");
		boolean insertStatus = authService.insertUser(appUser.getUserName(),
				passwordEncoder().encode(appUser.getUserPassword()), appUser.getUserRole());
		if (insertStatus) {
			System.out.println("Register success!...");
			return "redirect:/user/add?success=true";
		} else {
			System.out.println("Register failed!...");
			return "redirect:/user/add?success=false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/user/check/{username}", method = RequestMethod.GET)
	public  String checkUsername(@PathVariable("username") String username) {
		System.out.println("FrontEnd - Username exist!...");
		boolean isExist =  authService.isUsernameExist(username);
		if (isExist) {
			return "Success";
		}
		return "Failed";
	}
}
