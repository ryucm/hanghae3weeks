package com.example.week3.dto.response;

import com.example.week3.entity.Article;
import com.example.week3.entity.Heart;
import com.example.week3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ArticleResponseDto {
    private String title;
    private String content;
    private String author;

    private List<CommentResponseDto> commentResponseDtoList;

    private int heartNum;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ArticleResponseDto(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getMember().getNickname();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
    }

    public void updateArticle(List<Heart> heartList, List<CommentResponseDto> commentResponseDtoList) {
        if (!heartList.isEmpty())
            this.heartNum = heartList.size();
        this.commentResponseDtoList = commentResponseDtoList;

    }
}