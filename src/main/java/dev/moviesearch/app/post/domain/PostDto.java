package dev.moviesearch.app.post.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PostDto {

    @Parameter
    @Schema(description = "영화 인덱스")
    private int contentId;

    @Parameter
    @Schema(description = "영화 제목")
    private String title;

    @Parameter
    @Schema(description = "영화 개봉일")
    private Date releaseDate;

    @Parameter
    @Schema(description = "포스터 이미지")
    private String posterPath;

    private String originalLanguage;
    private BigDecimal popularity;
    private BigDecimal voteAverage;
    private int voteCount;
}
