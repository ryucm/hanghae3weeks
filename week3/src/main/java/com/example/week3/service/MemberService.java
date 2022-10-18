package com.example.week3.service;

import com.example.week3.dto.response.MemberResponseDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.repository.MemberRepository;
import com.example.week3.repository.RefreshTokenRepository;
import com.example.week3.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public ResponseDto<MemberResponseDto>
}
