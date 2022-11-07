package com.likelion.springmvc.domain.dto;

import com.likelion.springmvc.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;

    public ArticleDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(title, content);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
