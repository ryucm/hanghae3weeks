package com.example.week3.service;

import com.example.week3.entity.domain.Member;
import com.example.week3.jwt.TokenProvider;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@AllArgsConstructor
public class ServiceUtil {

    private final TokenProvider tokenProvider;

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (! tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

    boolean notAuthorized(HttpServletRequest request) {
        if (request.getHeader("Refresh-Token") == null) {
            return true;
        }

        if (request.getHeader("Authorization") == null) {
            return true;
        }
        return false;
    }
}
