package com.example.week3.repository;

import com.example.week3.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article delete(Long id);
}
