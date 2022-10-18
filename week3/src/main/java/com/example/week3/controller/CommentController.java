package com.example.week3.controller;


import com.example.week3.dto.request.CommentRequestDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.service.CommentService;
import com.example.week3.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    /*
    This controller handles APIs for both comment and likes of an article
     */
    private final CommentService commentService;

    @PostMapping("/article/{articleId}/comment")
    public ResponseDto<?> createComment(@PathVariable Long articleId, @RequestBody CommentRequestDto requestDto,
                                        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.createComment(articleId, requestDto, memberDetails);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseDto<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto,
                                        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.updateComment(commentId, requestDto, memberDetails);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.deleteComment(commentId, memberDetails);
    }

    @PutMapping("/article/{articleId}/heart")
    public ResponseDto<?> heartArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.heartArticle(articleId, memberDetails);
    }
}
