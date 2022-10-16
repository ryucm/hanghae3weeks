package com.example.week3.entity.comparator;

import com.example.week3.entity.domain.Post;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {
    @Override
    public int compare(Post p1, Post p2) {
        return p1.getModifiedAt().compareTo(p2.getModifiedAt());
    }
}
