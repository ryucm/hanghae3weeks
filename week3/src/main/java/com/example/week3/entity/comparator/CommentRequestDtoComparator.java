package com.example.week3.entity.comparator;

import com.example.week3.entity.request.CommentRequestDto;

import java.util.Comparator;

public class CommentRequestDtoComparator implements Comparator<CommentRequestDto> {


    @Override
    public int compare(CommentRequestDto c1, CommentRequestDto c2) {
        return c1.getModifiedAt().compareTo(c2.getModifiedAt());
    }
}