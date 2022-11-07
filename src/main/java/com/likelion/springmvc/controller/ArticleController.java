package com.likelion.springmvc.controller;

import com.likelion.springmvc.domain.dto.ArticleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @GetMapping("")
    public String home() {
        return "articles/index";
    }
    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/posts")
    public String createArticle(ArticleDTO form) {
        log.info(form.toString());
        return "redirect:";
    }
}
