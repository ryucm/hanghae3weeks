package com.example.week3.repository;

import com.example.week3.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article delete(Long id);
    @Override
    ArrayList<Article> findAll();
}
