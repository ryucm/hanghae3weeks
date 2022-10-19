package com.example.week3.service;

import com.example.week3.dto.request.CommentRequestDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ResponseDto<?> createComment(Long articleId, CommentRequestDto requestDto, MemberDetailsImpl memberDetails) {
        // validate article
        Article article = articleRepository.findById(articleId).orElseThrow(null);

        if (article == null) {
            return ResponseDto.fail("INVALID_ARTICLE_ID", "Article does not exist");
        }

        // validate member
        Member member = memberRepository.findByNickname(memberDetails.getUsername()).orElse(null);

        if (member == null) {
            return ResponseDto.fail("NULL_POINTER", "Member does not exist");
        }

        // create & save comment
        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .article(article)
                .member(member)
                .build();

        commentRepository.save(comment);

        return ResponseDto.success(new CommentResponseDto(comment));
    }
    public ResponseDto<?> updateComment(Long commentId, CommentRequestDto requestDto, MemberDetailsImpl memberDetails) {

        // validate comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(null);
        if (comment == null) {
            return ResponseDto.fail("INVALID_COMMENT_ID", "Comment does not exist");
        }

        // validate member
        Member member = memberDetails.getMember();

        if (! member.isEqual(comment.getMember())) {
            return ResponseDto.fail("NO_AUTHORITY", "Member does not have authority to change comment");
        }

        // update comment
        comment.update(requestDto);

        return ResponseDto.success(new CommentResponseDto(comment));
    }

    public ResponseDto<?> deleteComment(Long commentId, MemberDetailsImpl memberDetails) {
        // validate comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(null);

        if (comment == null) {
            return ResponseDto.fail("INVALID_COMMENT_ID", "Comment does not exist");
        }

        Member member = memberDetails.getMember();

        if (! member.isEqual(comment.getMember())) {
            return ResponseDto.fail("NO_AUTHORITY", "Member does not have authority to change comment");
        }

        // delete comment
        commentRepository.deleteById(commentId);

        return ResponseDto.success("Comment successfuly deleted");
    }


}
