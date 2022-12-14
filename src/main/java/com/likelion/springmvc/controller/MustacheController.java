package com.likelion.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {
    @GetMapping("")
    public String home() {
        return "index";
    }
    @GetMapping(value = "/hi/{id}")
    public String mustacheCon(Model model, @PathVariable("id") String id) {
        model.addAttribute("username", id);
        return "greetings";
    }
}
