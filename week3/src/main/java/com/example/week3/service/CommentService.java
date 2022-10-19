package com.example.week3.service;

import com.example.week3.dto.request.CommentRequestDto;
import com.example.week3.dto.request.MemberRequestDto;
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
    private final HeartRepository heartRepository;
//    private final ServiceUtil serviceUtil;

    public ResponseDto<?> createComment(Long articleId, CommentRequestDto requestDto, MemberDetailsImpl memberDetails) {
        // validate article
        Article article = articleRepository.findById(articleId).orElseThrow(null);

        if (article == null) {
            return ResponseDto.fail("INVALID_ARTICLE_ID", "Article does not exist");
        }

        // validate member
        MemberRequestDto memberRequestDto = requestDto.getMemberRequestDto();
        Member member = memberRepository.findByNickname(memberRequestDto.getNickname()).orElse(null);

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

    public ResponseDto<?> heartArticle(Long articleId, MemberDetailsImpl memberDetails) {

        // validate article
        Article article = articleRepository.findById(articleId).orElseThrow(null);
        // TODO: need to extract member information from request
        // previously implemented in serviceUtil
        // this member is the one who is liking/unliking the post

        if (article == null) {
            return ResponseDto.fail("INVALID_ARTICLE_ID", "Article does not exist");
        }

        Member member = memberDetails.getMember();

        // heart/unheart the article
        if (! heartRepository.existsByMemberAndArticle(member, article)) {
            // like the article
            Heart heart = Heart.builder()
                    .member(member)
                    .article(article)
                    .build();
            heartRepository.save(heart);
            return ResponseDto.success("Member liked the article");
        } else {
            Heart heart = heartRepository.findByMemberAndArticle(member, article).orElse(null);
            heartRepository.deleteById(heart.getId());
            return ResponseDto.success("Member unliked the article");
        }
    }
}
