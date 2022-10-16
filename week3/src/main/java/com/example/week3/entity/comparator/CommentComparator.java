package com.example.week3.entity.comparator;

import com.example.week3.entity.domain.Comment;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {

    @Override
    public int compare(Comment c1, Comment c2) {
        return c1.getModifiedAt().compareTo(c2.getModifiedAt());
    }
}
