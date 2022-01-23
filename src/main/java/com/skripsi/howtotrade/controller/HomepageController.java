package com.skripsi.howtotrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {

    @RequestMapping(value = "/")
    public String home(){
        return "home/homepage";
    }
    @RequestMapping(value = "/forum")
    public String forumPage(){
        return "forum";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }
}
