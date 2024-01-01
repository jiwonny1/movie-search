package dev.moviesearch.app.review.service;

import dev.moviesearch.app.review.domain.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> findReviewList();

//    ReviewDto findReview(int contentId);

    void deleteReview(int idx);

    void updateReview(ReviewDto req);

    void insertReview(ReviewDto req);
}
