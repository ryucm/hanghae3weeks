package com.example.week3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String content;
    private ArticleRequestDto articleRequestDto;
    private MemberRequestDto memberRequestDto;
}