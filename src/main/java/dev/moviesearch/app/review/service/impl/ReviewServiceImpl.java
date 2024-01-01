package dev.moviesearch.app.review.service.impl;

import dev.moviesearch.app.review.domain.ReviewDto;
import dev.moviesearch.app.review.mapper.ReviewMapper;
import dev.moviesearch.app.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> findReviewList() {
        log.info("===============================================================");
        log.info("로그 출력을 확인 합니다.");
        log.info("===============================================================");
        return reviewMapper.selectReviewList();
    }

//    @Override
//    public ReviewDto findReview(int idx) {
//        return reviewMapper.selectReview(idx);
//    }

    @Override
    public void insertReview(ReviewDto req) {
        reviewMapper.insertReview(req);
    }

    @Override
    public void updateReview(ReviewDto req) {
        reviewMapper.updateReview(req);
    }

    @Override
    public void deleteReview(int idx) {
        reviewMapper.deleteReview(idx);
    }
}
