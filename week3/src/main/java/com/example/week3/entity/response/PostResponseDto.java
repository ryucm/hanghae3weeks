package com.example.week3.entity.response;

import com.example.week3.entity.domain.Comment;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.comparator.CommentResponseDtoComparator;
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
public class PostResponseDto {
  private MemberResponseDto author;
  private String title;
  private String content;
  private TreeSet<CommentResponseDto> commentResponseDtos = new TreeSet<>(new CommentResponseDtoComparator());
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public PostResponseDto(Post post) {
    this.author = new MemberResponseDto(post.getAuthor());
    this.title = post.getTitle();
    this.content = post.getContent();

    for (Comment comment : post.getCommentList()) {
      this.commentResponseDtos.add(new CommentResponseDto((comment)));
    }
  }
}
