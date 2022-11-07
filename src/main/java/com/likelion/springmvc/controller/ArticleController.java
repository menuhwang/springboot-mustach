package com.likelion.springmvc.controller;

import com.likelion.springmvc.domain.dto.ArticleDTO;
import com.likelion.springmvc.domain.entity.Article;
import com.likelion.springmvc.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public String home(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/index";
    }
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        model.addAttribute("article", article);
        return "articles/detail";
    }
    @GetMapping("/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/posts")
    public String createArticle(ArticleDTO form) {
        log.info(form.toString());
        articleRepository.save(form.toEntity());
        return "redirect:";
    }
}
