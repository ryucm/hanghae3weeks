package com.example.week3.controller;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.entity.Article;
import com.example.week3.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성
    @PostMapping()
    public Article article(@RequestBody ArticleRequestDto requestDto) {
        return articleService.article(requestDto);
    }

}

