package com.example.week3.entity.response;

import com.example.week3.entity.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
  private MemberResponseDto author;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public CommentResponseDto(Comment comment) {
    this.author = new MemberResponseDto(comment.getAuthor());
    this.content = comment.getContent();
    this.createdAt = comment.getCreatedAt();
    this.modifiedAt = comment.getModifiedAt();
  }
}
