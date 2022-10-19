package com.example.week3.dto.response;

import com.example.week3.entity.Article;
import com.example.week3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private String title;
    private String content;
    private String author;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getMember().getNickname();
    }
}