package com.example.week3.entity.domain;

import com.example.week3.entity.Timestamped;
import com.example.week3.entity.request.CommentRequestDto;
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
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member author;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Post post;

    public Comment(CommentRequestDto requestDto) {
        this.author = new Member(requestDto.getAuthor());
        this.content = requestDto.getContent();
        this.post = new Post(requestDto.getPost());
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
