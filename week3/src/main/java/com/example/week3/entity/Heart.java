package com.example.week3.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "constraintMemberArticle",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = { "MEMBER_ID", "ARTICLE_ID" }
                )
        }
        )
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
