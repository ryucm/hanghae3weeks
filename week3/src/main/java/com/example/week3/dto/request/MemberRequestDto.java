package com.example.week3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
public class MemberRequestDto {

    private String nickname;
    private String password;
    private String passwordConfirm;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(nickname, password);
    }
}
