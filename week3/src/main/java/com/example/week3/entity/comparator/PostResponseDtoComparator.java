package com.example.week3.entity.comparator;

import com.example.week3.entity.response.PostResponseDto;

import java.util.Comparator;

public class PostResponseDtoComparator implements Comparator<PostResponseDto> {

    @Override
    public int compare(PostResponseDto p1, PostResponseDto p2) {
        return p1.getModifiedAt().compareTo(p2.getModifiedAt());
    }
}
