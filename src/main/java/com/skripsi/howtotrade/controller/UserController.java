package com.skripsi.howtotrade.controller;

import java.security.Principal;

import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    // Authentication authentication;
    //String userLogged = authentication.getName(); -- right now it still return null values

    @RequestMapping("")
    public String getUserProfile(Model model, Principal principal){
        model.addAttribute("profileForm", new Users());
        model.addAttribute("data", userService.getUserProfile(principal.getName())); //FIXME: when authentication has been set, change into 'userLogged'
        return "index/profile";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProfile(Users user){
        System.out.println("------------------user.getUserEmail(): "+user.getUserEmail());
        user.setUserId(userService.getUserId("alvin")); //FIXME: when authentication has been set, change into 'userLogged'
        userService.saveProfile(user);
        System.out.println("----------------Update user berhasil!");
        return "redirect:/profile";
    }
}
