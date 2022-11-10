package com.likelion.springmvc.controller;

import com.likelion.springmvc.domain.dto.ArticleDTO;
import com.likelion.springmvc.domain.dto.ReplyDTO;
import com.likelion.springmvc.domain.entity.Article;
import com.likelion.springmvc.domain.entity.Reply;
import com.likelion.springmvc.repository.ArticleRepository;
import com.likelion.springmvc.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

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
        model.addAttribute("replies", article.getReplies());
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
        log.info("{}", articleDTO);
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        article.updateTitle(articleDTO.getTitle());
        article.updateContent(articleDTO.getContent());
        Article saved = articleRepository.save(article); // 추후 비즈니스 레이어에서 트랜잭션 더티체킹 적용할 것.
        model.addAttribute("article", saved);
        model.addAttribute("replies", saved.getReplies());
        return "articles/detail";
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        log.info("{} 삭제", id);
        articleRepository.deleteById(id);
        return "articles/index";
    }

    @PostMapping("/{id}/reply")
    public String createReply(Model model, @PathVariable("id") Long id, ReplyDTO replyDTO) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
        Reply reply = replyDTO.toEntity();
        article.addReply(reply);
        replyRepository.save(reply);
        articleRepository.save(article);
        model.addAttribute("article", article);
        model.addAttribute("replies", article.getReplies());
        return "articles/detail";
    }

    @DeleteMapping("/{articleId}/reply/{replyId}")
    public String deleteReply(@PathVariable("replyId") Long replyId) {
        replyRepository.deleteById(replyId);
        return "articles/detail";
    }

    @PutMapping("/{articleId}/reply/{replyId}")
    public String editReply(@PathVariable("replyId") Long replyId, @RequestBody ReplyDTO dto) {
        log.info("id : {}, author : {}, content : {}", dto.getId(), dto.getAuthor(), dto.getContent());
//        replyRepository.save(dto.toEntity()); // PK값으로 값이 있는지 검색, 이미 있으면 업데이트 없으면 인서트
        return "articles/detail";
    }
}
