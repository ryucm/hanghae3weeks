package com.example.week3.controller;

import com.example.week3.dto.TokenDto;
import com.example.week3.dto.request.MemberRequestDto;
import com.example.week3.dto.request.TokenRequestDto;
import com.example.week3.dto.response.MemberResponseDto;
import com.example.week3.dto.response.ResponseDto;
import com.example.week3.repository.MemberRepository;
import com.example.week3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.signup(memberRequestDto);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {

        return memberService.login(memberRequestDto, response);
    }

    @PostMapping("/reissue")
    public ResponseDto<?> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto = new TokenDto();
        return ResponseDto.success(tokenDto);
    }
}
