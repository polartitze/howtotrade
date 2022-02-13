package com.skripsi.howtotrade.controller;

import javax.websocket.server.PathParam;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    //TopicMapper mapper;
    TopicService topicService;
    
    Authentication authentication;
    //String userLogged = authentication.getName(); -- right now it still return null values
    
    public TopicController(){
        
    }

    @RequestMapping("/all")
    public String getAllTopic(Model model){
        model.addAttribute("listTopic", topicService.getAllTopic());
        return "forum/topiclist";
    }

    @RequestMapping("/{topicId}")
    public String getTopicById(Model model, @PathVariable int topicId){
        model.addAttribute("topicData", topicService.getTopicById(topicId));
        model.addAttribute("listComment", topicService.getCommentOnTopic(topicId));
        model.addAttribute("comment", new Comment());
        return "forum/topic";
    }
    
    @RequestMapping(value = "/{topicId}/comment", method = RequestMethod.POST)
    public String saveCommentOnTopicId(Model model, @PathVariable int topicId, Comment comment){
        comment.setTopicId(topicId);
        comment.setUserId(topicService.getUserId("alvin"));  //FIXME: when authentication has been set, change into 'userLogged'

        topicService.insertComment(comment);
        model.addAttribute("comment", new Comment());
        return "redirect:/topic/{topicId}";
    }

}
