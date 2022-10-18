package com.example.week3.repository;

import com.example.week3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByNickname(String nickname);
    Optional<Member> findByNickname(String nickname);
}
