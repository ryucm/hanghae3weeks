package com.example.week3.controller;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.entity.Article;
import com.example.week3.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    /*
     * 게시글 전체 조회
     */
    @GetMapping
    public List<Article> selectAll() {
        return articleService.saveAll();
    }

    /*
     * 게시글 단일 조회
     */
    @GetMapping("/{articleId}")
    public Article findArticle(@PathVariable("articleId") Long id) {
        return articleService.findArticle(id);
    }

    /*
     * 게시글 생성
     */
    @PostMapping
    public Article article(@RequestBody ArticleRequestDto requestDto) {
        return articleService.article(requestDto);
    }

    /*
     * 게시글 수정
     */
    @PutMapping("/{articleId}")
    public Article update(@PathVariable("articleId") Long id, @RequestBody ArticleRequestDto requestDto) {
        return articleService.update(id, requestDto);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{articleId}")
    public void delete(@PathVariable("articleId") Long id) {
        articleService.delete(id);
    }

}

