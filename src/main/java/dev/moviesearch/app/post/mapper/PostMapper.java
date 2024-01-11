package dev.moviesearch.app.post.mapper;

import dev.moviesearch.app.post.domain.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDto> selectPostList();

    PostDto selectPost(int contentId);

//    void insertPost(PostDto req);

    void updatePost(PostDto req);

    void deletePost(int idx);
}
