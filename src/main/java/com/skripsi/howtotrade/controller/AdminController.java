package com.skripsi.howtotrade.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	
	@ModelAttribute("module")
    public String module() {
        return "manageuser";
    }
	
	//TOPIC
    @RequestMapping(value = "/manage-member", method = RequestMethod.GET)
    public String getAllMember(Model model, Principal principal){
        model.addAttribute("listUser", userService.getAllMember());
        return "admin/managemember";
    }
	
	//BLOCK USER ON TOPIC PAGE
    @RequestMapping("/manage-member/block/{username}")
    public String blockUser(@PathVariable String username){
        userService.blockUser(userService.getUserId(username));
        return "redirect:/admin/manage-member";
    }
    
    @RequestMapping("/manage-member/unblock/{username}")
    public String unblockMember(@PathVariable String username){
        userService.unblockUser(userService.getUserId(username));
        return "redirect:/admin/manage-member";
    }
    
    @RequestMapping("/manage-member/change/{username}")
    public String changetoExpert(@PathVariable String username){
    	userService.changetoExpert(userService.getUserId(username));
    	return "redirect:/admin/manage-member";
    }
    
}
