package com.likelion.springmvc.controller;

import com.likelion.springmvc.domain.dto.ArticleDTO;
import com.likelion.springmvc.domain.entity.Article;
import com.likelion.springmvc.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @PutMapping("/{id}")
    public String updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDTO articleDTO, Model model) {
        // Todo. 수정 후 리다이렉트
        log.info("{}", articleDTO);
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        article.updateTitle(articleDTO.getTitle());
        article.updateContent(articleDTO.getContent());
        Article saved = articleRepository.save(article); // 추후 비즈니스 레이어에서 트랜잭션 더티체킹 적용할 것.
        model.addAttribute("article", saved);
        return "";
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        log.info("{} 삭제", id);
        articleRepository.deleteById(id);
        return "";
    }
}
