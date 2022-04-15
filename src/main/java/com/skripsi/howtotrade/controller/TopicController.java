package com.skripsi.howtotrade.controller;


import java.security.Principal;

import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
import com.skripsi.howtotrade.service.TopicService;

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
    public String addNewTopic(Topic topic, Principal principal){
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
        topicService.blockMember(topicService.getUserId(username)); //FIXME: when authentication has been set, change into 'userLogged'
        return "redirect:/topic/all";
    }
}
