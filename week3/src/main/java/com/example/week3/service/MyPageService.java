//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.week3.service;

import com.example.week3.dto.response.ArticleResponseDto;
import com.example.week3.dto.response.CommentResponseDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.entity.Article;
import com.example.week3.entity.Comment;
import com.example.week3.entity.Heart;
import com.example.week3.entity.Member;
import com.example.week3.repository.ArticleRepository;
import com.example.week3.repository.CommentRepository;
import com.example.week3.repository.HeartRepository;
import com.example.week3.repository.MemberRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;

    public ResponseDto<?> getMyArticles(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElseThrow(() ->
            new UsernameNotFoundException("닉네임을 찾을 수 없습니다."));

        List<Article> myArticleList = articleRepository.findAllByMember(member);
        List<ArticleResponseDto> articleResponseDtoList = myArticleList.stream().map(ArticleResponseDto::new).collect(Collectors.toList());

        return ResponseDto.success(articleResponseDtoList);
    }

    public ResponseDto<?> getMyComments(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElseThrow(() ->
           new UsernameNotFoundException("닉네임을 찾을 수 없습니다."));

        List<Comment> myCommentList = commentRepository.findAllByMember(member);
        List<CommentResponseDto> commentResponseDtoList = myCommentList.stream().map(CommentResponseDto::new).collect(Collectors.toList());

        return ResponseDto.success(commentResponseDtoList);
    }

    public ResponseDto<?> getMyHeartArticles(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElseThrow(() ->
            new UsernameNotFoundException("닉네임을 찾을 수 없습니다."));

        List<Heart> myHeartList = heartRepository.findAllByMember(member);
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for(Heart heart : myHeartList) {

            articleResponseDtoList.add(new ArticleResponseDto(articleRepository.findById(heart.getArticle().getId())
                    .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."))));
        }

        return ResponseDto.success(articleResponseDtoList);
    }

}
