package com.skripsi.howtotrade.controller;


import java.io.IOException;
import java.security.Principal;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.service.TopicService;
import com.skripsi.howtotrade.utility.Constant;
import com.skripsi.howtotrade.utility.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;
    
    String userLogged;
    
    
    public TopicController(){
    }
    
    @ModelAttribute("module")
    public String module() {
        return "forum";
    }
    
    //TOPIC
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllTopic(Model model, Principal principal){

        model.addAttribute("listTopic", topicService.getAllTopic());
        model.addAttribute("topicForm", new Topic());
        return "forum/topiclist";
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public String getTopicById(Model model, @PathVariable int topicId, Principal principal){
        model.addAttribute("topicData", topicService.getTopicById(topicId));
        model.addAttribute("listComment", topicService.getCommentOnTopic(topicId));
        model.addAttribute("commentForm", new Comment());
        return "forum/topic";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewTopic(Topic topic, Principal principal, @RequestParam("image") MultipartFile multipartFile){
        String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constant.TOPIC_IMAGE_PATH + principal.getName() + "/topic/" + topic.getTopicTitle();
        
        if(filename == null || "".equals(filename)){
            topic.setImagePath("-");
        } 
        else{
            topic.setImagePath(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
            } catch (IOException exception) {
                System.out.println("File uploaded with error");
                exception.printStackTrace();
            }
        } 

        topic.setAuthorId(topicService.getUserId(principal.getName()));

        topicService.insertTopic(topic);
        return "redirect:/topic/all";
    }
    
    @RequestMapping("/{topicId}/delete")
    public String deleteTopicById(@PathVariable int topicId, Principal principal){
        topicService.deleteTopic(topicService.getUserId(principal.getName()), topicId);
        return "redirect:/topic/all";
    }

    //COMMENT
    @RequestMapping(value = "/{topicId}/comment", method = RequestMethod.POST)
    public String saveCommentOnTopicId(Model model, @PathVariable int topicId, Comment comment, Principal principal){
        comment.setTopicId(topicId);
        comment.setUserId(topicService.getUserId(principal.getName()));
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
        topicService.blockMember(topicService.getUserId(username));
        return "redirect:/topic/all";
    }
}
