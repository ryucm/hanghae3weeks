package com.example.week3.entity.comparator;

import com.example.week3.entity.request.PostRequestDto;

import java.util.Comparator;

public class PostRequestDtoComparator implements Comparator<PostRequestDto> {

    @Override
    public int compare(PostRequestDto p1, PostRequestDto p2) {
        return p1.getModifiedAt().compareTo(p2.getModifiedAt());
    }
}
