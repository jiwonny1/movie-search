package dev.moviesearch.app.review.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ReviewDto {
    private String userId;
    private int contentId;
    private int star;
    private String content;

}
