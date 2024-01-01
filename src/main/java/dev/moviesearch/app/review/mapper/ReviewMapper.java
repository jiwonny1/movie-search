package dev.moviesearch.app.review.mapper;

import dev.moviesearch.app.review.domain.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewDto> selectReviewList();

    ReviewDto selectReview(int idx);

    void insertReview(ReviewDto req);

    void updateReview(ReviewDto req);

    void deleteReview(int idx);
}
