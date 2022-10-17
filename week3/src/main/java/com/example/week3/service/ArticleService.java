package com.example.week3.service;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.entity.Article;
import com.example.week3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article article(ArticleRequestDto requestArticle) {
        Article article = new Article(requestArticle);
        return articleRepository.save(article);
    }
}
