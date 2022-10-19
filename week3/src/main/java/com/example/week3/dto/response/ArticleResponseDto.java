package com.example.week3.dto.response;

import com.example.week3.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String content;
    private MemberResponseDto memberResponseDto;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.memberResponseDto = new MemberResponseDto(article.getMember());
    }
}