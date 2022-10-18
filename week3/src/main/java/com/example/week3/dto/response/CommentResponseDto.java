package com.example.week3.dto.response;

import com.example.week3.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private ArticleResponseDto articleResponseDto;
    private MemberResponseDto memberResponseDto;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.articleResponseDto = new ArticleResponseDto(comment.getArticle());
        this.memberResponseDto = new MemberResponseDto(comment.getMember());
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}