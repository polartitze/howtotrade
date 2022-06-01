package com.skripsi.howtotrade.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.skripsi.howtotrade.model.Mail;
import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.AuthService;
import com.skripsi.howtotrade.service.MailService;
import com.skripsi.howtotrade.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
    @Autowired
	private AuthService authService;
	
	@Autowired
    private MailService mailService;

	@ModelAttribute("module")
    private String module() {
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
        
        //SEND MAIL
        String content = "";
        try {
			StringBuilder contentBuilder = new StringBuilder();
			FileReader fileIn = new FileReader("src/main/resources/templates/index/emailblock.html");
			BufferedReader br = new BufferedReader(fileIn);
			String str;
			while ((str = br.readLine()) != null) {
				if(str.contains("@")) {
					int count = str.length() - str.replaceAll("@","").length();
					if(count > 1) {
						System.out.println("Replacing parameter... ");
						String param = str.substring(str.indexOf("@")+1, str.lastIndexOf("@"));
						String replacedBy = username;
						String willBeReplace = "@"+param+"@";
						str = str.replaceAll(willBeReplace, replacedBy);
					}
				}
				contentBuilder.append(str);
			}
			br.close();
			content = contentBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
        Mail mail = new Mail();
		Users users = userService.findUserAccount(username);
		mail.setMailFrom("polaritze@gmail.com"); //TODO: MAKE BUSSINESS EMAIL
		mail.setMailTo(users.getUserEmail());
		mail.setMailSubject("Account has been blocked!");
		mail.setMailContent(content);
		mailService.sendEmail(mail);


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
