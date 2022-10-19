package com.example.week3.dto.response;

import com.example.week3.entity.Member;

public class MemberResponseDto {
    private Long id;
    private String nickname;
    private String password;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.password = member.getPassword();
    }
}