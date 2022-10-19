package com.example.week3.controller;

import com.example.week3.dto.response.ResponseDto;
import com.example.week3.service.MemberDetailsImpl;
import com.example.week3.service.MyPageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mypage"})
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping({"/articles"})
    public ResponseDto<?> getMyArticles(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return this.myPageService.getMyArticles(memberDetails.getUsername());
    }

    @GetMapping({"/comments"})
    public ResponseDto<?> getMyComments(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return this.myPageService.getMyComments(memberDetails.getUsername());
    }

    @GetMapping({"/hearts"})
    public ResponseDto<?> getMyHearts(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return this.myPageService.getMyHeartArticles(memberDetails.getUsername());
    }

    public MyPageController(final MyPageService myPageService) {
        this.myPageService = myPageService;
    }
}
