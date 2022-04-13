package com.skripsi.howtotrade.controller;

import java.security.Principal;

import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        user.setUserId(userService.getUserId(principal.getName()));
        
        System.out.println("------------------Updating profile");
        System.out.println(user.getUserPassword());
        
        if("".equals(user.getUserPassword()) || user.getUserPassword() == null){
            userService.saveProfile(user.getUserEmail(), user.getUserId());
        }else{
            String encoded = passwordEncoder().encode(user.getUserPassword());
            System.out.println("encoded: "+encoded); 
            userService.saveProfileWithPassword(user.getUserEmail(), encoded, user.getUserId());
        }
        
        System.out.println("----------------Update user berhasil!");
        return "redirect:/profile";
    }

    @ResponseBody
    @RequestMapping(value = "/check-old-pass", method = RequestMethod.GET)
    public boolean checkOldPassword(@RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("username") String username){
        String dbPassword = userService.getOldPassword(username);
        System.out.println(username);
        System.out.println(dbPassword);
        if(passwordEncoder().matches(oldPassword, dbPassword)){
            System.out.println("old pass match");
            return true;
        }
        return false;
    }
}
