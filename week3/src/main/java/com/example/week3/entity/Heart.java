package com.example.week3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "MEMBER_ID", "ARTICLE_ID" }) })
public class Heart {

    @Column(name = "MEMBER_ID")
    private Member member;

    @Column(name = "ARTICLE_ID")
    private Article article;
}
