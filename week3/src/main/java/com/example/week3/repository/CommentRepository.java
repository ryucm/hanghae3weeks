package com.example.week3.repository;

import com.example.week3.entity.Article;
import com.example.week3.entity.Comment;
import com.example.week3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByMember(Member member);
    List<Comment> findAllByArticle(Article article);
}
