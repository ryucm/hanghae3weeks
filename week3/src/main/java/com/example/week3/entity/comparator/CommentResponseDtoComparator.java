package com.example.week3.entity.comparator;

import com.example.week3.entity.response.CommentResponseDto;

import java.util.Comparator;

public class CommentResponseDtoComparator implements Comparator<CommentResponseDto> {

    @Override
    public int compare(CommentResponseDto c1, CommentResponseDto c2) {
        return c1.getModifiedAt().compareTo(c2.getModifiedAt());
    }
}
