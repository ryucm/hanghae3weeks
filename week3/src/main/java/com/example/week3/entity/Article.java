package com.example.week3.entity;

import com.example.week3.dto.request.ArticleRequestDto;
import com.example.week3.util.TimeStamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    public Article(ArticleRequestDto requestArticle) {
        this.title = requestArticle.getTitle();
        this.content = requestArticle.getContent();
    }

    public void update(String title, String content) {
        if(this.title != title) {
            this.title = title;
        }
        if(this.content != content) {
            this.content = content;
        }
    }


}
