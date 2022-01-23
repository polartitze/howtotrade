package com.skripsi.howtotrade.controller;

import com.skripsi.howtotrade.mapper.TopicMapper;
import com.skripsi.howtotrade.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    //TopicMapper mapper;
    TopicService topicService;

    public TopicController(){
        
    }

    @RequestMapping("/all")
    public String getAllTopic(Model model){

        model.addAttribute("listTopic", topicService.getAllTopic());

        return "forum/topiclist";
    }

}
