package com.example.week3.service;

import com.example.week3.entity.domain.Comment;
import com.example.week3.entity.domain.Member;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.request.PostRequestDto;
import com.example.week3.entity.response.PostResponseDto;
import com.example.week3.entity.response.ResponseDto;
import com.example.week3.jwt.TokenProvider;
import com.example.week3.repository.CommentRepository;
import com.example.week3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final TokenProvider tokenProvider;


    @Transactional
    public ResponseDto<?> getAllPosts() {
        // TODO: Sort by date created/modified
        return ResponseDto.success(postRepository.findAll());
    }

    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto, HttpServletRequest request) {
        // Validation & Authentication
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "No Authorization");
        }

        Member member = validateMember(request);

        if (member == null) {
            return ResponseDto.fail("INVALID_TOKEN", "Token is invalid");
        }

        // Create & save post
        Post post = new Post(requestDto);

        member.addPost(post);

        postRepository.save(post);

        return ResponseDto.success(new PostResponseDto(post));
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (! tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

    @Transactional
    public ResponseDto getPost(Long id) {

        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).orElse(null);
            return ResponseDto.success(new PostResponseDto(post));
        } else {
            return ResponseDto.fail("INVALID_POST_ID", "Post does not exist");
        }
    }

    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        // Validation & Authorization
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        // update post

        if (postRepository.existsById(id)) {
            Post post =  postRepository.findById(id).orElse(null);
            post.update(requestDto);
            return ResponseDto.success(new PostResponseDto(post));
        } else {
            return ResponseDto.fail("INVALID_ID", "Post does not exist");
        }


    }

    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        // Validation & Authorization
        if (notAuthorized(request)) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        // delete post

        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).orElse(null);

            // delete all relevant comments
            for (Comment comment : post.getComments()) {
                commentRepository.delete(comment);
            }

            member.deletePost(post);
            postRepository.deleteById(id);
            return ResponseDto.success("Post deleted successfuly");
        } else {
            return ResponseDto.fail("INVALID_POST_ID", "Post deletion failed");
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
