package com.elitec.nexusgaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

    @GetMapping("/catalogue")
    public String catalogue(){
        return "catalogue";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
