package com.likelion.springmvc.repository;

import com.likelion.springmvc.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
