package com.example.week3.service;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.dto.response.ArticleResponseDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.entity.Article;
import com.example.week3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    /*
     * 게시글 단일 조회
     */
    public ResponseDto<?> findArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);

        if(article == null ) {
            return ResponseDto.fail("Invalied id", "Article id를 찾을 수 없음");
        }

        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

        return ResponseDto.success(articleResponseDto);
    }

    /*
     * 게시글 전체조회
     */
    public ResponseDto<?> saveAll() {
        ArrayList<Article> findArticles = articleRepository.findAll();

        List<ArticleResponseDto> articles = new ArrayList<>();
        for (Article article : findArticles) {
            articles.add(new ArticleResponseDto(article));
        }

        return ResponseDto.success(articles);
    }

    /*
     * 게시글 등록
     */
    @Transactional
    public ResponseDto<?> article(ArticleRequestDto requestArticle, MemberDetailsImpl memberDetails) {
        Article article = new Article(requestArticle, memberDetails.getMember());
        articleRepository.save(article);
        return ResponseDto.success("성공적으로 저장했습니다.");
    }

    /*
     * 게시글 수정
     */
    @Transactional
    public ResponseDto<?> update(Long id, ArticleRequestDto requestDto, MemberDetailsImpl memberDetails) {

        Article article = articleRepository.findById(id).orElse(null);

        if(article == null) {
            return ResponseDto.fail("Invalied id", "수정할 게시글이 없음");
        }

        if(article.getMember().getId().equals(memberDetails.getMember().getId())) {
            String title = requestDto.getTitle();
            String content = requestDto.getContent();

            article.update(title,content);

            articleRepository.save(article);
            return ResponseDto.success("성공");
        } else {
            return ResponseDto.fail("수정권한이 없어","수정권한이 없어요");
        }
    }

    /*
     * 게시글 삭제
     */
    @Transactional
    public ResponseDto<?> delete(Long id, MemberDetailsImpl memberDetails) {
        Article article = articleRepository.findById(id).orElse(null);

        if(article == null) {
            return ResponseDto.fail("해당 게시글이 없어요","게시글 없음!");
        }

        if(article.getMember().getId().equals(memberDetails.getMember().getId())) {
            articleRepository.deleteById(id);
            return ResponseDto.success("삭제성공");
        } else {
            return ResponseDto.fail("권한이 없어요","권한없음");
        }
    }

}
