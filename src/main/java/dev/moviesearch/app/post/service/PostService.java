package dev.moviesearch.app.post.service;

import dev.moviesearch.app.post.domain.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findPostList();

    PostDto findPost(int idx);

    void deletePost(int idx);

    void updatePost(PostDto req);

//    void insertPost(PostDto req);
}
