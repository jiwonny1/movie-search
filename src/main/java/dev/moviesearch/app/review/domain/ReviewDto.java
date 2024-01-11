package dev.moviesearch.app.review.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ReviewDto {

    @Parameter
    @Schema(description = "리뷰 작성 아이디")
    private String userId;

    @Parameter
    @Schema(description = "영화 인덱스")
    private int contentId;

    @Parameter
    @Schema(description = "영화 리뷰 평점")
    private int star;

    @Parameter
    @Schema(description = "리뷰 내용")
    private String content;

}
