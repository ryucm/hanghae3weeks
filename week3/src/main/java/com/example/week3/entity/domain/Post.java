package com.example.week3.entity.domain;

import com.example.week3.entity.Timestamped;
import com.example.week3.entity.comparator.CommentComparator;
import com.example.week3.entity.request.CommentRequestDto;
import com.example.week3.entity.request.MemberRequestDto;
import com.example.week3.entity.request.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToMany
    private Set<Member> likes = new HashSet<>();

    @OneToMany
    private TreeSet<Comment> comments = new TreeSet<>(new CommentComparator());

    // TODO: Builder pattern

    public Post(PostRequestDto requestDto) {
        this.author = new Member(requestDto.getAuthor());
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

        if (requestDto.getCommentRequestDtos() != null) {
            for (CommentRequestDto commentRequestDto : requestDto.getCommentRequestDtos()) {
                this.comments.add(new Comment(commentRequestDto));
            }
        }
    }

    public void update(PostRequestDto requestDto) {
        // No need to update member & commentList
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void like(Member member) {
        likes.add(member);
    }

    public void unlike(Member member) {
        likes.remove(member);
    }
}
