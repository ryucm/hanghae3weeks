package com.example.week3.repository;

import com.example.week3.entity.Article;
import com.example.week3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByMember(Member member);
}
