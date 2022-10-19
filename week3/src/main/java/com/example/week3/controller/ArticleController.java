package com.example.week3.controller;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.service.ArticleService;
import com.example.week3.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/article"})
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public ResponseDto<?> selectAll() {
        return articleService.findAllArticle();
    }

    @PostMapping
    public ResponseDto<?> article(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.article(requestDto, memberDetails);
    }

    @GetMapping({"/{articleId}"})
    public ResponseDto<?> findArticle(@PathVariable("articleId") Long id) {
        return articleService.findArticle(id);
    }

    @PutMapping({"/{articleId}"})
    public ResponseDto<?> update(@PathVariable("articleId") Long id, @RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.update(id, requestDto, memberDetails);
    }

    @DeleteMapping({"/{articleId}"})
    public ResponseDto<?> delete(@PathVariable("articleId") Long id, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.delete(id, memberDetails);
    }
}


