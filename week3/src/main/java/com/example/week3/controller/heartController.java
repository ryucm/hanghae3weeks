package com.example.week3.controller;


import com.example.week3.dto.response.ResponseDto;
import com.example.week3.service.HeartService;
import com.example.week3.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class heartController {

    private final HeartService heartService;

    @PutMapping("/article/{articleId}/heart")
    public ResponseDto<?> heartArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return heartService.heartArticle(articleId, memberDetails);
    }
}
