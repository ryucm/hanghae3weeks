package com.example.week3.entity.response;

import com.example.week3.entity.domain.Member;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.comparator.PostResponseDtoComparator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
  private String nickname;
  private String password;
  private TreeSet<PostResponseDto> postResponseDtos = new TreeSet<>(new PostResponseDtoComparator());
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public MemberResponseDto(Member member) {
    this.nickname = member.getNickname();
    this.password = member.getPassword();

    for (Post post : member.getPosts()) {
      this.postResponseDtos.add(new PostResponseDto(post));
    }

    this.createdAt = member.getCreatedAt();
    this.modifiedAt = member.getModifiedAt();
  }
}
