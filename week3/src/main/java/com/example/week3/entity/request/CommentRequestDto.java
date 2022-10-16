package com.example.week3.entity.request;

import com.example.week3.entity.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
  private MemberRequestDto author;
  private String content;
  private PostRequestDto post;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public CommentRequestDto(Comment comment) {
    this.author = new MemberRequestDto(comment.getAuthor());
    this.content = comment.getContent();
    this.createdAt = comment.getCreatedAt();
    this.modifiedAt = comment.getModifiedAt();
  }
}
