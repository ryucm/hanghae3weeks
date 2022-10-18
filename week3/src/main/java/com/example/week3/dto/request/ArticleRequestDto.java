package com.example.week3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequestDto {
    private Long id;
    private String title;
    private String content;
    private MemberRequestDto memberRequestDto;
}