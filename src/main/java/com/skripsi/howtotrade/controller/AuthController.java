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

import com.skripsi.howtotrade.model.Mail;
import com.skripsi.howtotrade.service.MailService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.AuthService;

@Controller
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@Autowired
    private MailService mailSender;
    private Mail mail = new Mail();
    
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	String username;
	@ModelAttribute("module")
    public String module() {
        return "home";
    }
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request){
    	if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/topic/all";
        }
    	else if (request.isUserInRole("ROLE_PRO")) {
            return "redirect:/pro/course/all";
        }
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
		boolean insertStatus = authService.insertUser(appUser.getUserName(), appUser.getUserEmail(),
				passwordEncoder().encode(appUser.getUserPassword()), appUser.getUserRole(), appUser.getUserRealName());
		if (insertStatus) {
			System.out.println("Register success!...");
			return "redirect:/user/add?success=true&username="+appUser.getUserName();
		} else {
			System.out.println("Register failed!...");
			return "redirect:/user/add?success=false";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/user/check/{username}", method = RequestMethod.GET)
	public  String checkUsername(@PathVariable("username") String username) {
		String isExist =  authService.isUsernameExist(username);
		return isExist;
	}

	@ResponseBody
	@RequestMapping(value = "/user/check-mail/{email}", method = RequestMethod.GET)
	public  String checkMail(@PathVariable("email") String email) {
		String isExist =  authService.checkMail(email);
		System.out.println("isExist: "+isExist);
		return isExist;
	}
	
	@RequestMapping(value = "/validate-account/{username}", method = RequestMethod.GET)
	public String validateAccount(@PathVariable("username") String username) {
		//if clicked will verified the account
		authService.validateAccount(username);
		return "index/emailverification";
	}
	
	
	
	@RequestMapping(value = "/validate-mail/{username}", method = RequestMethod.GET)
	public String validateMail(@PathVariable("username") String username) {
		String content = "";

		try {
			StringBuilder contentBuilder = new StringBuilder();
			FileReader fileIn = new FileReader("src/main/resources/templates/index/emailverify.html");
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
		Users users = authService.userDetaiil(username);
		mail.setMailFrom("polaritze@gmail.com"); //TODO: MAKE BUSSINESS EMAIL
		mail.setMailTo(users.getUserEmail());
		mail.setMailSubject("Verify your email!");
		mail.setMailContent(content);
		mailSender.sendEmail(mail);
		return "index/validate";
	}


}
