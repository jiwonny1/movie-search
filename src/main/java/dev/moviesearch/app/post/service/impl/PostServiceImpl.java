package dev.moviesearch.app.post.service.impl;

import dev.moviesearch.app.post.domain.PostDto;
import dev.moviesearch.app.post.mapper.PostMapper;
import dev.moviesearch.app.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostDto> findPostList() {
        log.info("===============================================================");
        log.info("로그 출력을 확인 합니다.");
        log.info("===============================================================");
        return postMapper.selectPostList();
    }

    @Override
    public PostDto findPost(int contentId) {
        return postMapper.selectPost(contentId);
    }

//    @Override
//    public void insertPost(PostDto req) {
//        postMapper.insertPost(req);
//    }

    @Override
    public void updatePost(PostDto req) {
        postMapper.updatePost(req);
    }

    @Override
    public void deletePost(int idx) {
        postMapper.deletePost(idx);
    }
}
