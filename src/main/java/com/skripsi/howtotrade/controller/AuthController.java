package com.skripsi.howtotrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.howtotrade.model.Users;
// import com.skripsi.howtotrade.security.UserDetailsServiceImpl;
import com.skripsi.howtotrade.service.AuthService;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;
	
	// @Autowired
	// private UserDetailsServiceImpl userDetailsServiceImpl;

	

    @RequestMapping(value = "/")
    public String landing(){
        return "index/homepage";
    }

	@RequestMapping(value = "/home")
    public String home(){
        return "index/successpage";
    }

	@RequestMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("users", new Users());
		return "index/register";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Users users) {
		System.out.println(users.getUserName());
		// validasi user sudah terdaftar atau belum
		if(authService.isUsernameExist(users.getUserName())) {
			System.out.println("Username sudah terdaftar");
			return "redirect:/";
		}
		// insert to db
		authService.insertUser(users);
		return "redirect:/";
	}
	
	//halaman login
	@RequestMapping(value = "/login.html")
    public String login(Model model){
		// model.addAttribute("loginForm", new Users());
        return "index/login";
    }

	//halaman error-login
	// @RequestMapping(value = "/login.html")
    // public String errorLogin(Model model){
	// 	model.addAttribute("loginError", true);
    //     return "index/login";
    // }
	
	@RequestMapping(value = "/doLogin")
    public String doLogin(Model model, Users user){
		
		//Boolean isExistUsername = authService.isUsernameExist(user.getUserName());
		//Boolean isExistPassword = authService.checkPassword(user.getUserName(), user.getUserPassword());
		// if(!isExistUsername){
		// 	model.addAttribute("ErrorMessage", "Username belum terdaftar");
		// 	model.addAttribute("loginForm", new Users());
		// 	return "index/login";
		// }else{
		// 	if(!isExistPassword){
		// 		model.addAttribute("ErrorMessage", "Kata sandi tidak sesuai");
		// 	return "redirect:/login";
		// 	}
		// }

		// userDetailsServiceImpl.loadUserByUsername(user.getUserName());
		
        return "index/successpage";
    }
}
