package com.example.week3.service;

import com.example.week3.dto.response.ResponseDto;
import com.example.week3.entity.Article;
import com.example.week3.entity.Heart;
import com.example.week3.entity.Member;
import com.example.week3.repository.ArticleRepository;
import com.example.week3.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HeartService {


    private final ArticleRepository articleRepository;
    private final HeartRepository heartRepository;

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
