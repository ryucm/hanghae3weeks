package com.example.week3.service;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.entity.Article;
import com.example.week3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    /*
     * 게시글 단일 조회
     */

    public Article findArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("잘못된 요청입니다.")
        );

        return article;
    }

    /*
     * 게시글 전체조회
     */
    public List<Article> saveAll() {
        return articleRepository.findAll();
    }

    /*
     * 게시글 등록
     */
    @Transactional
    public Article article(ArticleRequestDto requestArticle) {
        Article article = new Article(requestArticle);
        return articleRepository.save(article);
    }

    /*
     * 게시글 수정
     */
    @Transactional
    public Article update(Long id, ArticleRequestDto requestDto) {

        Article article = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("잘못된 요청입니다.")
        );

        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        article.update(title,content);

        return articleRepository.save(article);
    }

    /*
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("잘못된 요청입니다.")
        );

        articleRepository.deleteById(id);
    }
}
