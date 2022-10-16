package com.example.week3.entity.domain;

import com.example.week3.entity.Timestamped;
import com.example.week3.entity.comparator.PostComparator;
import com.example.week3.entity.request.MemberRequestDto;
import com.example.week3.entity.request.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.TreeSet;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE)
    private TreeSet<Post> posts = new TreeSet<>(new PostComparator());

    public Member(MemberRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.password = requestDto.getPassword();

        if (requestDto.getPostRequestDtos() != null) {
            for (PostRequestDto postRequestDto : requestDto.getPostRequestDtos()) {
                this.posts.add(new Post(postRequestDto));
            }
        }
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public void deletePost(Post post) {
        posts.remove(post);
    }
}

