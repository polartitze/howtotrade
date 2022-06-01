package com.skripsi.howtotrade.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.security.Principal;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Mail;
import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.AuthService;
import com.skripsi.howtotrade.service.MailService;
import com.skripsi.howtotrade.service.TopicService;
import com.skripsi.howtotrade.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
	private AuthService authService;
	
	@Autowired
    private MailService mailSender;

    public TopicController(){
    }
    
    @ModelAttribute("module")
    private String module() {
        return "forum";
    }

    //TOPIC
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllTopic(Model model, Principal principal){
        try {
            model.addAttribute("data", userService.findUserAccount(principal.getName()));
        } catch (Exception e) {
            System.out.println("LOG: USER NOT AUTHENTICATED");
            e.printStackTrace();
        }
        model.addAttribute("listTopic", topicService.getAllTopic());
        model.addAttribute("topicForm", new Topic());
        return "forum/topiclist";
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public String getTopicById(Model model, @PathVariable int topicId, Principal principal){
        int authorId = topicService.getAuthor(topicId);
        model.addAttribute("data", userService.findUserAccount(userService.getUserName(authorId)));
        model.addAttribute("topicData", topicService.getTopicById(topicId));
        model.addAttribute("listComment", topicService.getCommentOnTopic(topicId));
        model.addAttribute("commentForm", new Comment());
        return "forum/topic";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewTopic(Topic topic, Principal principal){
        // String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // String uploadDir = Constant.TOPIC_IMAGE_PATH + principal.getName() + "/topic/" + topic.getTopicTitle();
        
        // if(filename == null || "".equals(filename)){
        //     topic.setImagePath("-");
        // } 
        // else{
        //     topic.setImagePath(filename);
        //     try {
        //         FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
        //     } catch (IOException exception) {
        //         System.out.println("File uploaded with error");
        //         exception.printStackTrace();
        //     }
        // } 

        topic.setAuthorId(userService.getUserId(principal.getName()));

        topicService.insertTopic(topic);
        return "redirect:/topic/all";
    }
    
    @RequestMapping("/{topicId}/delete")
    public String deleteTopicById(@PathVariable int topicId, Principal principal){
    	if(userService.getUserRole(principal.getName()).equals("ROLE_ADMIN")) {
    		topicService.deleteTopicAdmin(topicId);
    	}
    	else topicService.deleteTopic(userService.getUserId(principal.getName()), topicId);
        return "redirect:/topic/all";
    }

    //COMMENT
    @RequestMapping(value = "/{topicId}/comment", method = RequestMethod.POST)
    public String saveCommentOnTopicId(Model model, @PathVariable int topicId, Comment comment, Principal principal){
        comment.setTopicId(topicId);
        comment.setUserId(userService.getUserId(principal.getName()));
        topicService.insertComment(comment);
        model.addAttribute("commentForm", new Comment());
        return "redirect:/topic/{topicId}";
    }

    @RequestMapping("/{topicId}/comment/{commentId}/delete")
    public String deleteCommentOnTopicId(@PathVariable int topicId, @PathVariable int commentId){
        topicService.deleteComment(commentId, topicId);
        return "redirect:/topic/{topicId}";
    }

    //BLOCK USER ON TOPIC PAGE
    @RequestMapping("/block/{username}")
    public String blockMember(@PathVariable String username){
        topicService.blockMember(userService.getUserId(username));

        //SEND MAIL
		Mail mail = new Mail();
        
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
        Users users = authService.userDetaiil(username);
        mail.setMailFrom("polaritze@gmail.com");
        mail.setMailTo(users.getUserEmail());
        mail.setMailSubject("Account has been blocked!");
        mail.setMailContent(content);
        mailSender.sendEmail(mail);

        return "redirect:/topic/all";
    }
}
