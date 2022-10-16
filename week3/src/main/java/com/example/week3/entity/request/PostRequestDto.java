package com.example.week3.entity.request;

import com.example.week3.entity.domain.Comment;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.comparator.CommentRequestDtoComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
  private MemberRequestDto author;
  private String title;
  private String content;
  private TreeSet<CommentRequestDto> commentRequestDtos = new TreeSet<>(new CommentRequestDtoComparator());
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public PostRequestDto(Post post) {
    this.author = new MemberRequestDto(post.getAuthor());
    this.title = post.getTitle();
    this.content = post.getContent();

    for (Comment comment : post.getComments()) {
      this.commentRequestDtos.add(new CommentRequestDto(comment));
    }
  }
}
