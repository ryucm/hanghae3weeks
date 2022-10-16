package com.example.week3.controller;


import com.example.week3.entity.request.CommentRequestDto;
import com.example.week3.entity.response.ResponseDto;
import com.example.week3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseDto<?> addComment(@PathVariable Long postId, CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.addComment(postId, requestDto, request);
    }

    @PutMapping("/update/{postId}/{commentId}")
    public ResponseDto<?> updateComment(@PathVariable Long postId, @PathVariable Long commentId, CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.updateComment(postId, commentId, requestDto, request);
    }

    @DeleteMapping("/delete/{postId}/{commentId}")
    public ResponseDto<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest request) {
        return commentService.deleteComment(postId, commentId, request);
    }
}
