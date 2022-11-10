package com.likelion.springmvc.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<Reply> replies = new ArrayList<>();

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void addReply(Reply reply) {
        reply.setArticle(this);
        replies.add(reply);
    }

    public void updateTitle(String title) {
        if (title != null) this.title = title;
    }

    public void updateContent(String content) {
        if (content != null) this.content = content;
    }
}
