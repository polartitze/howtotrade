package com.skripsi.howtotrade.controller;

import java.security.Principal;

import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @RequestMapping("")
    public String getUserProfile(Model model, Principal principal){
        model.addAttribute("profileForm", new Users());
        model.addAttribute("data", userService.getUserProfile(principal.getName()));
        return "index/profile";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProfile(Users user, Principal principal){
        System.out.println("------------------Updating profile");
        user.setUserId(userService.getUserId(principal.getName()));
        userService.saveProfile(user.getUserEmail(), passwordEncoder().encode(user.getUserPassword()), user.getUserId());
        System.out.println("----------------Update user berhasil!");
        return "redirect:/profile";
    }

}
