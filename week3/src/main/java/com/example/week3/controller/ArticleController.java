package com.example.week3.controller;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.service.ArticleService;
import com.example.week3.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /*
     * 게시글 전체 조회
     */
    @GetMapping
    public ResponseDto<?> selectAll() {
        return ResponseDto.success(articleService.saveAll());
    }

    /*
     * 게시글 단일 조회
     */
    @GetMapping("/{articleId}")
    public ResponseDto<?> findArticle(@PathVariable("articleId") Long id) {
        return ResponseDto.success(articleService.findArticle(id));
    }

    /*
     * 게시글 생성
     */
    @PostMapping
    public ResponseDto<?> article(@RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.article(requestDto, memberDetails);
    }

    /*
     * 게시글 수정
     */
    @PutMapping("/{articleId}")
    public ResponseDto<?> update(@PathVariable("articleId") Long id, @RequestBody ArticleRequestDto requestDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.update(id, requestDto,memberDetails);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{articleId}")
    public ResponseDto<?> delete(@PathVariable("articleId") Long id, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return articleService.delete(id, memberDetails);
    }

}

