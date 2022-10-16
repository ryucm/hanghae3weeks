package com.example.week3.entity.request;

import com.example.week3.entity.domain.Member;
import com.example.week3.entity.domain.Post;
import com.example.week3.entity.comparator.PostRequestDtoComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.TreeSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

  @NotBlank(message = "{member.nickname.notblank}")
  @Size(min = 4, max = 12, message = "{member.nickname.size}")
  @Pattern(regexp = "[a-z\\d]*${3,12}", message = "{member.nickname.pattern}")
  private String nickname;

  @NotBlank(message = "{member.password.notblank}")
  @Size(min = 8, max = 20, message = "{member.password.size}")
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$"
          , message = "{member.password.pattern}")
  private String password;

  @NotBlank
  private String passwordConfirm;

  private TreeSet<PostRequestDto> postRequestDtos = new TreeSet<>(new PostRequestDtoComparator());

  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public MemberRequestDto(Member member) {
    this.nickname = member.getNickname();
    this.password = member.getPassword();

    for (Post post : member.getPosts()) {
      this.postRequestDtos.add(new PostRequestDto(post));
    }

    this.createdAt = member.getCreatedAt();
    this.modifiedAt = member.getModifiedAt();
  }
}
