package com.example.week3.service;

import com.example.week3.entity.domain.Comment;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.request.CommentRequestDto;
import com.example.week3.entity.response.ResponseDto;
import com.example.week3.repository.CommentRepository;
import com.example.week3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public ResponseDto addComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request) {
        // Validation & Authorization
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        // add comment
        Comment comment = new Comment(requestDto);

        if (postRepository.existsById(postId)) {
            comment.getPost().getComments().add(comment);
            commentRepository.save(comment);
            return ResponseDto.success("Comment successfuly added");
        } else {
            return ResponseDto.fail("INVALID_POST_ID", "Post does not exist");
        }
    }

    public ResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, HttpServletRequest request) {
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (postRepository.existsById(postId) && commentRepository.existsById(commentId)) {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            comment.update(requestDto);
            return ResponseDto.success(("Comment successfully updated"));
        } else {
            return ResponseDto.fail("INVALID_TOKEN", "Invalid tokens were given");
        }
    }

    public ResponseDto deleteComment(Long postId, Long commentId, HttpServletRequest request) {
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (postRepository.existsById(postId) && commentRepository.existsById(commentId)) {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            Post post = postRepository.findById(postId).orElse(null);
            post.getComments().remove(comment);
            commentRepository.deleteById(commentId);
            return ResponseDto.success(("Comment successfully deleted"));
        } else {
            return ResponseDto.fail("INVALID_TOKEN", "Invalid tokens were given");
        }
    }

    boolean notAuthorized(HttpServletRequest request) {
        if (request.getHeader("Refresh-Token") == null) {
            return true;
        }

        if (request.getHeader("Authorization") == null) {
            return true;
        }
        return false;
    }
}
