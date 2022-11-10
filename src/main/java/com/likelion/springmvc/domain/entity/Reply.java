package com.likelion.springmvc.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String content;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Reply(Long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
