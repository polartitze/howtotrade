package com.skripsi.howtotrade.controller;


import com.skripsi.howtotrade.mapper.UserMapper;
import com.skripsi.howtotrade.model.Comment;
import com.skripsi.howtotrade.model.Topic;
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
    //TOPIC
    @RequestMapping("/all")
    public String getAllTopic(Model model){
        System.out.println("==========="+topicService.getAllTopic());
        model.addAttribute("listTopic", topicService.getAllTopic());
        model.addAttribute("topicForm", new Topic());
        model.addAttribute("userLogged", "alvin"); //FIXME: when authentication has been set, change into 'userLogged'
        model.addAttribute("roles", topicService.getRole("alvin")); //FIXME: when authentication has been set, change into 'userLogged'
        return "forum/topiclist";
    }

    @RequestMapping("/{topicId}")
    public String getTopicById(Model model, @PathVariable int topicId){
        model.addAttribute("topicData", topicService.getTopicById(topicId));
        model.addAttribute("listComment", topicService.getCommentOnTopic(topicId));
        model.addAttribute("commentForm", new Comment());
        model.addAttribute("roles", topicService.getRole("alvin")); //FIXME: when authentication has been set, change into 'userLogged'
        return "forum/topic";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewTopic(Topic topic){
        topic.setAuthorId(topicService.getUserId("alvin")); //FIXME: when authentication has been set, change into 'userLogged'
        topicService.insertTopic(topic);
        return "redirect:/topic/all";
    }
    
    @RequestMapping("/{topicId}/delete")
    public String deleteTopicById(@PathVariable int topicId){
        topicService.deleteTopic(topicService.getUserId("alvin"), topicId); //FIXME: when authentication has been set, change into 'userLogged'
        return "redirect:/topic/all";
    }

    //COMMENT
    @RequestMapping(value = "/{topicId}/comment", method = RequestMethod.POST)
    public String saveCommentOnTopicId(Model model, @PathVariable int topicId, Comment comment){
        comment.setTopicId(topicId);
        comment.setUserId(topicService.getUserId("alvin"));  //FIXME: when authentication has been set, change into 'userLogged'
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
