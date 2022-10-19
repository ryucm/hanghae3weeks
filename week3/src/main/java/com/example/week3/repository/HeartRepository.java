package com.example.week3.repository;

import com.example.week3.entity.Article;
import com.example.week3.entity.Heart;
import com.example.week3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAndArticle(Member member, Article article);
    Optional<Heart> findByMemberAndArticle(Member member, Article article);

    List<Heart> findAllByMember(Member member);
    List<Heart> findAllByArticle(Article article);
}