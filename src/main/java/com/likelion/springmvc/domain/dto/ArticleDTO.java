package com.likelion.springmvc.domain.dto;

import com.likelion.springmvc.domain.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;

    public ArticleDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(id, title, content);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
